package com.example.ds2022_30241_fariseu_teodora.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterDTO {
    @NotBlank(message="Please pick an username")
    @Size(min=5, max=20,message = "Username must have between 5 and 20 characters")
    private String username;
    @NotBlank(message = "e-mail field is mandatory")
    @Pattern(regexp="^[^0-9]+[0-9]*([.|-|_][\\w]+)*@[\\w]+(-[\\w]+)*[.][\\w]+", message="Please provide a valid email address")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    @Size(min=5, max=24,message = "Password must have between 5 and 24 characters")
    private String password;
}
