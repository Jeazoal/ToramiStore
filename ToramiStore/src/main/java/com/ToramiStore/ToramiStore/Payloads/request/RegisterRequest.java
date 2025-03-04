package com.ToramiStore.ToramiStore.Payloads.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String nombre;
    private String apellidos;
    private String correo;
    private String dni;
    private String numero;
    private String password;
}
