package com.example.ds2022_30241_fariseu_teodora.service;

import com.example.ds2022_30241_fariseu_teodora.dto.consumption.ConsumptionDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.consumption.ReadConsumptionDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.device.DeviceDTO;
import com.example.ds2022_30241_fariseu_teodora.entity.EnergyConsumption;
import com.example.ds2022_30241_fariseu_teodora.entity.MonitoringDevice;
import com.example.ds2022_30241_fariseu_teodora.repository.ConsumptionRepo;
import com.example.ds2022_30241_fariseu_teodora.repository.DeviceModelRepo;
import com.example.ds2022_30241_fariseu_teodora.repository.DeviceRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ConsumptionService {
    @Autowired
    protected ConsumptionRepo repo;
    @Autowired
    protected DeviceRepo deviceRepo;
    @Autowired
    protected ModelMapper modelMapper;
    public void addConsumption(ReadConsumptionDTO reading) {
        EnergyConsumption consumption = new EnergyConsumption();
        consumption.setTimestamp(reading.timestampLocalDateTime());
        consumption.setValueConsumed(reading.getMeasurementValue());
        MonitoringDevice device = new MonitoringDevice();
        device.setId(reading.getDeviceId());
        consumption.setSourceDevice(device);
        repo.save(consumption);
    }
    public List<ConsumptionDTO> allFromDay(DeviceDTO device, LocalDate date) {
        List<ConsumptionDTO> list = repo.findAllBySourceDeviceAndTimestampGreaterThanEqualAndTimestampLessThanEqualOrderByTimestampDesc(
                        modelMapper.map(device, MonitoringDevice.class),
                        date.atStartOfDay(), date.atStartOfDay().plusDays(1))
                .stream().map(energyConsumption -> ConsumptionDTO.builder().amount(energyConsumption.getValueConsumed())
                        .label(energyConsumption.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm"))).build())
                .collect(Collectors.toList());
        Collections.reverse(list);
        return list;
    }
    public List<ConsumptionDTO> allFromDay(String userID,LocalDate date) {
        List<ConsumptionDTO> partialResult = repo.energyForUserByDay(userID, date.atStartOfDay(), date.atStartOfDay().plusDays(1))
                .stream().map(count -> ConsumptionDTO.builder().amount(count[0]).label(count[1]+":00").build()).collect(Collectors.toList());
        return partialResult;
    }

    public Double exceedsConsumption(ReadConsumptionDTO readConsumption) {
        MonitoringDevice device = new MonitoringDevice();
        device.setId(readConsumption.getDeviceId());
        LocalDateTime hour = readConsumption.timestampLocalDate().atStartOfDay().plusHours(readConsumption.timestampLocalDateTime().getHour());
        List<EnergyConsumption> onHour = repo
                .findAllBySourceDeviceAndTimestampGreaterThanEqualAndTimestampLessThanEqualOrderByTimestampDesc(device,
                        hour,
                        hour.plusHours(1));
        Double curr = 0.0;
        if(onHour.size()>1) {
            curr = onHour.get(onHour.size()-1).getValueConsumed()-onHour.get(0).getValueConsumed();
        }
        else {
            List<EnergyConsumption> lastHour = repo
                    .findAllBySourceDeviceAndTimestampGreaterThanEqualAndTimestampLessThanEqualOrderByTimestampDesc(device,
                            hour.plusHours(-1),
                            hour);
            if(onHour.isEmpty())
                curr = lastHour.get(0).getValueConsumed();
            else
                if(lastHour.isEmpty())
                    curr = 0.0;
                else
                    curr = onHour.get(0).getValueConsumed()-lastHour.get(lastHour.size()-1).getValueConsumed();
        }
        device = deviceRepo.findById(readConsumption.getDeviceId()).get();
        log.info("Consumption for device "+readConsumption.getDeviceId()+" for hour "+
                readConsumption.timestampLocalDateTime().getHour()+" is " +curr+" and the max consumption for device is "+device.getDeviceModel().getMaxDeviceConsumption());

        return curr - device.getDeviceModel().getMaxDeviceConsumption();
    }

    public boolean exceeded(ReadConsumptionDTO readConsumption) {
        MonitoringDevice device = new MonitoringDevice();
        device.setId(readConsumption.getDeviceId());
        LocalDateTime hour = readConsumption.timestampLocalDate().atStartOfDay().plusHours(readConsumption.timestampLocalDateTime().getHour());
        List<EnergyConsumption> onHour = repo
                .findAllBySourceDeviceAndTimestampGreaterThanEqualAndTimestampLessThanEqualOrderByTimestampDesc(device,
                        hour,
                        hour.plusHours(1));

        device = deviceRepo.findById(readConsumption.getDeviceId()).get();
        return onHour.get(onHour.size()-1).getValueConsumed() > device.getDeviceModel().getMaxDeviceConsumption();
    }

}
