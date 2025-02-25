package com.ToramiStore.ToramiStore.Payloads.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditUserRequest {
    private String nombre;
    private String apellidos;
    private String correo;
    private String dni;
    private String numero;
}
