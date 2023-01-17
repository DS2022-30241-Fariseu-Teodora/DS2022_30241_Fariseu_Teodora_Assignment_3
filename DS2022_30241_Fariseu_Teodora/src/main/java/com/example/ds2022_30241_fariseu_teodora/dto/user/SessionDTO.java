package com.example.ds2022_30241_fariseu_teodora.dto.user;

import com.example.ds2022_30241_fariseu_teodora.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionDTO {
    private String id;
    private String username;
    private Role role;
}
