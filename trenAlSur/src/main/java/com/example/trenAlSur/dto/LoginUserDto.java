package com.example.trenAlSur.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserDto {

    private String usuario;
    private String contraseña;


}