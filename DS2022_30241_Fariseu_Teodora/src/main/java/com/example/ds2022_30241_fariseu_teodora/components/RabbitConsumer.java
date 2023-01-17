package com.example.ds2022_30241_fariseu_teodora.components;

import com.example.ds2022_30241_fariseu_teodora.dto.consumption.ConsumptionDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.consumption.ReadConsumptionDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.notification.NotificationDTO;
import com.example.ds2022_30241_fariseu_teodora.service.ConsumptionService;
import com.example.ds2022_30241_fariseu_teodora.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@Controller
public class RabbitConsumer {
    @Autowired
    SimpMessagingTemplate template;
    @Autowired
    ConsumptionService service;
    @Autowired
    NotificationService notificationService;


    @Autowired
    ModelMapper modelMapper;
    @RabbitListener(queues = "energy-queue")
    public String receiveMessage(ReadConsumptionDTO consumptionDTO) {
        log.info("Recieved Message From RabbitMQ: " + consumptionDTO.getMeasurementValue()+" for "+consumptionDTO.getDeviceId());
        service.addConsumption(consumptionDTO);
        Double exceeded = service.exceedsConsumption(consumptionDTO);
        if(service.exceeded(consumptionDTO) && notificationService.shouldSendNotification(consumptionDTO)){
            NotificationDTO newNotification = new NotificationDTO();
            newNotification.setDeviceID(consumptionDTO.getDeviceId());
            newNotification.setExceeded(-exceeded);
            newNotification.setMessage("Consumption exceeded by "+-exceeded);
            log.info("Energy exceeded. Sending notification");
            try {
                notificationService.saveNotification(newNotification);
                template.convertAndSend("/topic/notifications/"
                                +notificationService.getRecipient(consumptionDTO.getDeviceId())
                                ,newNotification);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        ConsumptionDTO consumption = new ConsumptionDTO();
        consumption.setLabel(consumptionDTO.timestampLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm")));
        consumption.setAmount(consumptionDTO.getMeasurementValue());
        template.convertAndSend("/topic/device/"+consumptionDTO.getDeviceId(),consumption);
        return null;
    }

    @SendTo("/topic/device/{id}")
    public ConsumptionDTO broadcastMessage(@Payload ConsumptionDTO consumptionDTO) {
        return modelMapper.map(consumptionDTO, ConsumptionDTO.class);
    }
    @SendTo("/topic/notifications/{id}")
    public NotificationDTO notifyUser(@Payload NotificationDTO dto) {
        return modelMapper.map(dto,NotificationDTO.class);
    }
}