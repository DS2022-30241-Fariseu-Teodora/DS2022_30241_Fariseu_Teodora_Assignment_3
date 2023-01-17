package com.example.ds2022_30241_fariseu_teodora.dto.device;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceUserDTO extends NewDeviceDTO{
    private String id;
    private String description;
    private String address;
}
