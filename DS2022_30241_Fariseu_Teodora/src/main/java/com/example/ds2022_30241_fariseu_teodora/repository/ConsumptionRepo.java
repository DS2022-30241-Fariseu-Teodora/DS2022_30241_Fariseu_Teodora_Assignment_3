package com.example.ds2022_30241_fariseu_teodora.repository;

import com.example.ds2022_30241_fariseu_teodora.entity.EnergyConsumption;
import com.example.ds2022_30241_fariseu_teodora.entity.MonitoringDevice;
import com.example.ds2022_30241_fariseu_teodora.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsumptionRepo extends JpaRepository<EnergyConsumption, String> {
    List<EnergyConsumption> findAllBySourceDeviceAndTimestampGreaterThanEqualAndTimestampLessThanEqualOrderByTimestampDesc(MonitoringDevice device, LocalDateTime date1, LocalDateTime date2);
    @Query(
            value = "SELECT SUM(energy_consumption.value_consumed),EXTRACT(HOUR FROM energy_consumption.timestamp) FROM energy_consumption\n" +
                    "INNER JOIN monitoring_device d ON d.device_id = energy_consumption.device_id WHERE d.user_id = ?1\n" +
                    "AND energy_consumption.timestamp >= ?2 AND energy_consumption.timestamp < ?3" +
                    " GROUP BY EXTRACT(HOUR FROM energy_consumption.timestamp);",
            nativeQuery = true
    )
    List<Double[]> energyForUserByDay(String userID,LocalDateTime start, LocalDateTime end);
    @Query(
            value = "SELECT SUM(energy_consumption.value_consumed) FROM energy_consumption \n" +
                    "WHERE energy_consumption.device_id = ?1 \n" +
                    "AND energy_consumption.timestamp >= ?2 AND energy_consumption.timestamp < ?3" ,
            nativeQuery = true
    )
    Double energyForHour(String deviceId, LocalDateTime day);
}
