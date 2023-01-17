package com.example.ds2022_30241_fariseu_teodora.dto.device;

import com.example.ds2022_30241_fariseu_teodora.dto.deviceModel.DeviceModelDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.user.SimpleUserDTO;
import com.example.ds2022_30241_fariseu_teodora.entity.SiteUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.lang.reflect.Field;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceDTO extends DeviceUserDTO{
    @Setter(AccessLevel.NONE)
    private SimpleUserDTO owner;

    public void setOwner(SiteUser owner) {
        if(owner == null){
            this.owner = null;
            return;
        }
        this.owner = SimpleUserDTO.builder()
                .id(owner.getId())
                .username(owner.getUsername()).build();
    }
    @SuppressWarnings("unchecked")
    @JsonProperty("owner")
    private void unpackNested(Map<String,Object> deviceModelFields) {;
        if(deviceModelFields.isEmpty())
            return;
        owner = new SimpleUserDTO();
        deviceModelFields.forEach((fieldName, value) -> {
            try {
                Field field = owner.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                if(value instanceof Integer){
                    field.set(owner,Float.valueOf((Integer)value));
                }
                else field.set(owner,value);
                field.setAccessible(false);
            } catch (NoSuchFieldException e) {
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
