package com.example.ds2022_30241_fariseu_teodora.service;

import com.example.ds2022_30241_fariseu_teodora.dto.device.DeviceDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.device.DeviceInstanceDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.device.DeviceUserDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.deviceModel.DeviceModelDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.device.NewDeviceDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.user.SimpleUserDTO;
import com.example.ds2022_30241_fariseu_teodora.entity.DeviceModel;
import com.example.ds2022_30241_fariseu_teodora.entity.MonitoringDevice;
import com.example.ds2022_30241_fariseu_teodora.entity.SiteUser;
import com.example.ds2022_30241_fariseu_teodora.repository.DeviceModelRepo;
import com.example.ds2022_30241_fariseu_teodora.repository.DeviceRepo;
import com.example.ds2022_30241_fariseu_teodora.repository.SiteUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DeviceService {
    @Autowired
    protected DeviceRepo repo;
    @Autowired
    protected SiteUserRepo userRepo;
    @Autowired
    protected DeviceModelRepo deviceModelRepo;
    @Autowired
    protected ModelMapper modelMapper;

    public List<DeviceDTO> allDevices() {
        return repo.findAll().stream().map(device -> modelMapper.map(device,DeviceDTO.class)).collect(Collectors.toList());
    }
    public List<DeviceUserDTO> allDevicesFor(SimpleUserDTO user){
        return repo.findAllByOwner(modelMapper.map(user, SiteUser.class)).stream()
                .map(device-> modelMapper.map(device,DeviceUserDTO.class)).collect(Collectors.toList());
    }
    public List<DeviceInstanceDTO> allDevicesFor(DeviceModelDTO model) {
        return repo.findAllByDeviceModel(modelMapper.map(model, DeviceModel.class)).stream()
                .map(device-> modelMapper.map(device, DeviceInstanceDTO.class)).collect(Collectors.toList());
    }
    public void addDevice(NewDeviceDTO newDevice) {
        try {
            MonitoringDevice device = modelMapper.map(newDevice, MonitoringDevice.class);
            repo.save(device);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DeviceDTO getDevice(String deviceID) throws Exception {
        MonitoringDevice deviceDAO = repo.findById(deviceID).orElse(null);
        if(deviceDAO != null)
            return modelMapper.map(deviceDAO, DeviceDTO.class);
        throw new Exception("Not found");
    }

    public void removeDevice(DeviceDTO device) {
        try {
            repo.delete(modelMapper.map(device, MonitoringDevice.class));
        }catch (Exception e) {
            throw e;
        }
    }

    public void associateDevice(DeviceDTO device) {
        MonitoringDevice deviceDAO = repo.findById(device.getId()).orElse(null);
        if(deviceDAO != null) {
            deviceDAO.setOwner(userRepo.findById(device.getOwner().getId()).orElse(null));

        }
        repo.save(deviceDAO);
    }
}
