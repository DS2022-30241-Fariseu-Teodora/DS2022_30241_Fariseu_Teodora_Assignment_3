package com.example.ds2022_30241_fariseu_teodora.dto.mappers;
import com.example.ds2022_30241_fariseu_teodora.dto.user.EditProfileDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.user.UserProfileDTO;
import com.example.ds2022_30241_fariseu_teodora.entity.SiteUser;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDTO(EditProfileDTO source, @MappingTarget SiteUser entity);
}
