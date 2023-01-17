package com.example.ds2022_30241_fariseu_teodora.controller;

import com.example.ds2022_30241_fariseu_teodora.dto.consumption.ConsumptionDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.device.DeviceDTO;
import com.example.ds2022_30241_fariseu_teodora.security.AuthenticateDTO;
import com.example.ds2022_30241_fariseu_teodora.service.ConsumptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/device/energy")
public class ConsumptionController {
    @Autowired
    protected ConsumptionService service;
    @GetMapping("/dayConsumption")
    public ResponseEntity<List<ConsumptionDTO>> dayConsumption() {
        AuthenticateDTO session = (AuthenticateDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(service.allFromDay(session.getId(),LocalDate.now()));
    }
    @GetMapping("/dayConsumption/{date}")
    public ResponseEntity<List<ConsumptionDTO>> dayConsumption(@PathVariable String date) {
        AuthenticateDTO session = (AuthenticateDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(service.allFromDay(session.getId(),LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
    }
    @GetMapping("/deviceConsumption/{deviceID}")
    public ResponseEntity<List<ConsumptionDTO>> getDeviceConsumption(@PathVariable String deviceID) {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId(deviceID);
        return ResponseEntity.ok(service.allFromDay(deviceDTO, LocalDate.now()));
    }
    @GetMapping("/deviceConsumption/{deviceID}/{date}")
    public ResponseEntity<List<ConsumptionDTO>> getDeviceConsumption(@PathVariable String deviceID, @PathVariable String date) {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId(deviceID);
        return ResponseEntity.ok(service.allFromDay(deviceDTO, LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
    }
    @PostMapping("/add")
    public ResponseEntity<String> addConsumption(String deviceID, Integer amount) {
        return ResponseEntity.ok("Consumption registered");
    }
}
