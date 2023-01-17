package com.example.ds2022_30241_fariseu_teodora.repository;

import com.example.ds2022_30241_fariseu_teodora.entity.DeviceModel;
import com.example.ds2022_30241_fariseu_teodora.entity.MonitoringDevice;
import com.example.ds2022_30241_fariseu_teodora.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepo extends JpaRepository<MonitoringDevice, String> {
    List<MonitoringDevice> findAllByOwner(SiteUser user);
    List<MonitoringDevice> findAllByDeviceModel(DeviceModel model);
}
