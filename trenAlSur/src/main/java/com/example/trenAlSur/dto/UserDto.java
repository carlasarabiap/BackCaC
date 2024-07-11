package com.example.trenAlSur.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Integer id;
    private String nombre;
    private String apellido;
    private String fenaci;
    private String pais;
    private String email;
    private String usuario;
    private String contraseña;



}
