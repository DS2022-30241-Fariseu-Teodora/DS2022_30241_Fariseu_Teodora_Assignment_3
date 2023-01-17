package com.example.ds2022_30241_fariseu_teodora.repository;

import com.example.ds2022_30241_fariseu_teodora.entity.DeviceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceModelRepo extends JpaRepository<DeviceModel, String> {
    List<DeviceModel> findByModelNameContaining(String name);
}
