package com.example.ds2022_30241_fariseu_teodora.controller;

import com.example.ds2022_30241_fariseu_teodora.dto.deviceModel.DeviceModelDTO;
import com.example.ds2022_30241_fariseu_teodora.service.DeviceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/devices/models")
public class DeviceModelController {
    @Autowired
    protected DeviceModelService deviceModelService;

    @GetMapping(value = "/")
    public ResponseEntity<List<DeviceModelDTO>> allModels() { return ResponseEntity.ok(deviceModelService.getAll()); }

    @GetMapping(value = "/searchModel")
    public ResponseEntity<List<DeviceModelDTO>> findModelBy(@RequestParam String modelName){
        return ResponseEntity.ok(deviceModelService.findByModelName(modelName));
    }

    @PostMapping(value = "/addDeviceModel")
    public ResponseEntity<String> addModel(@RequestBody @Valid DeviceModelDTO model) {
        try{
            deviceModelService.addModel(model);
            return ResponseEntity.ok("Data added successfully");
        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
