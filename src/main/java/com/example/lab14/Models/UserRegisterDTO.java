package com.example.lab14.Models;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String email;
    private String role;
}
