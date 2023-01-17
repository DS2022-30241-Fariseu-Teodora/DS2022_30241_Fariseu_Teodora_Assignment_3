package com.example.energy_simulator.configurations;

import com.example.energy_simulator.fileReading.FileReader;
import com.example.energy_simulator.model.EnergyReading;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.Random;

@Configuration
@EnableScheduling
public class ScheduledConfiguration {
    Logger logger = LoggerFactory.getLogger(ScheduledConfiguration.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private FileReader fileReader = new FileReader();
    @Value("${mydevices.device.id1}")
    private String deviceId1;
    @Value("${mydevices.device.id2}")
    private String deviceId2;
    @Scheduled(fixedRate = 1 * 1000)
    public void scheduleFixedRateTask() {
        EnergyReading reading = new EnergyReading();
        reading.setReading(fileReader.readFile());
        reading.setTimestamp((double) new Date().getTime());
        reading.setDeviceId(deviceId1);
        EnergyReading reading2 = new EnergyReading();
        reading2.setReading(reading.getReading()+ new Random().nextInt(20));
        reading2.setTimestamp((double) new Date().getTime());
        reading2.setDeviceId(deviceId2);
        logger.info("Sending readings for devices "+deviceId1+","+deviceId2+"....");
        rabbitTemplate.convertAndSend(AMPQConfiguration.topicExchangeName,AMPQConfiguration.ROUTING_KEY, reading);
        rabbitTemplate.convertAndSend(AMPQConfiguration.topicExchangeName,AMPQConfiguration.ROUTING_KEY, reading2);

    }
}
