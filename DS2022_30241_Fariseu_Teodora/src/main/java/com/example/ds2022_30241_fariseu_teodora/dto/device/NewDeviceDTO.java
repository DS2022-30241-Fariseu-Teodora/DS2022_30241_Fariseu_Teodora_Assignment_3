package com.example.ds2022_30241_fariseu_teodora.dto.device;

import com.example.ds2022_30241_fariseu_teodora.dto.deviceModel.DeviceModelDTO;
import com.example.ds2022_30241_fariseu_teodora.entity.DeviceModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Map;

@Getter
@Setter
@Slf4j
public class NewDeviceDTO {
    private String id;
    private String description;
    private String address;

    @Setter(AccessLevel.NONE)
    private DeviceModelDTO deviceModel;

    public void setDeviceModel(DeviceModel deviceModel) {
        if(deviceModel == null) {
            this.deviceModel = null;
            return;
        }
        this.deviceModel = DeviceModelDTO.builder()
                .id(deviceModel.getId())
                .modelName(deviceModel.getModelName())
                .maxDeviceConsumption(deviceModel.getMaxDeviceConsumption())
                .serialNumber(deviceModel.getSerialNumber()).build();

    }
    @SuppressWarnings("unchecked")
    @JsonProperty("deviceModel")
    private void unpackNested(Map<String,Object> deviceModelFields) {
        if(deviceModelFields.isEmpty())
            return;
        deviceModel = new DeviceModelDTO();
        deviceModelFields.forEach((fieldName, value) -> {
            try {
                Field field = deviceModel.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                if(value instanceof Integer){
                    field.set(deviceModel,Float.valueOf((Integer)value));
                }
                else field.set(deviceModel,value);
                field.setAccessible(false);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
