package com.example.ds2022_30241_fariseu_teodora.service;

import com.example.ds2022_30241_fariseu_teodora.dto.deviceModel.DeviceModelDTO;
import com.example.ds2022_30241_fariseu_teodora.entity.DeviceModel;
import com.example.ds2022_30241_fariseu_teodora.repository.DeviceModelRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceModelService {
    @Autowired
    protected DeviceModelRepo repo;
    @Autowired
    protected ModelMapper modelMapper;

    public List<DeviceModelDTO> findByModelName(String modelName) {
        return repo.findByModelNameContaining(modelName).stream().map( model -> modelMapper.map(model, DeviceModelDTO.class)).collect(Collectors.toList());
    }
    public List<DeviceModelDTO> getAll() {
        return repo.findAll().stream().map( model -> modelMapper.map(model, DeviceModelDTO.class)).collect(Collectors.toList());

    }

    public void addModel(DeviceModelDTO newModel) {
        repo.save(modelMapper.map(newModel, DeviceModel.class));
    }
}
