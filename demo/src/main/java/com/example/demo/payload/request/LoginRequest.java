package com.example.demo.payload.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
//сервис, создает пользователя. и контроллер будет сохранять
public class LoginRequest {

    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    private String password;


}
