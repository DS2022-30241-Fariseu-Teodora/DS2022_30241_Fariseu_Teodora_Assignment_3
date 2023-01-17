package com.example.ds2022_30241_fariseu_teodora.controller;

import com.example.ds2022_30241_fariseu_teodora.dto.device.DeviceDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.device.DeviceInstanceDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.device.DeviceUserDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.device.NewDeviceDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.deviceModel.DeviceModelDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.user.SimpleUserDTO;
import com.example.ds2022_30241_fariseu_teodora.security.AuthenticateDTO;
import com.example.ds2022_30241_fariseu_teodora.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/devices")
public class DevicesController {
    @Autowired
    protected DeviceService deviceService;
    @GetMapping(value = "/")
    public ResponseEntity<List<DeviceDTO>> allDevices() {
        return ResponseEntity.ok(deviceService.allDevices());
    }
    @GetMapping(value = "/userDevices/{userId}")
    public ResponseEntity<List<DeviceUserDTO>> allDevicesFor(@PathVariable String userId) {
        return ResponseEntity.ok(deviceService.allDevicesFor(SimpleUserDTO.builder().id(userId).build()));
    }
    @GetMapping(value = "/{deviceID}")
    public ResponseEntity<DeviceDTO> getDevice(@PathVariable String deviceID) {
        try {
            return ResponseEntity.ok(deviceService.getDevice(deviceID));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/modelDevices/{modelID}")
    public ResponseEntity<List<DeviceInstanceDTO>> allDevicesForModel(@PathVariable String modelID) {
        return ResponseEntity.ok(deviceService.allDevicesFor(DeviceModelDTO.builder().id(modelID).build()));
    }

    @GetMapping(value = "/myDevices")
    public ResponseEntity<List<DeviceUserDTO>> myDevices() {
        AuthenticateDTO session = (AuthenticateDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Getting devices for "+session.getId()+" "+session.getUsername());
        return ResponseEntity.ok(deviceService.allDevicesFor(SimpleUserDTO.builder().id(session.getId()).build()));
    }
    @PostMapping(value = "/addDevice")
    public ResponseEntity<String> addDevice(@RequestBody @Valid NewDeviceDTO device) {
        try{
            deviceService.addDevice(device);
            return ResponseEntity.ok("Data added successfully");
        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }
    @PutMapping (value = "/associateDevice")
    public ResponseEntity<String> associate(@RequestBody DeviceDTO deviceDTO) {
        try {
            deviceService.associateDevice(deviceDTO);
            return ResponseEntity.ok("Added user association");
        }
        catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

}
