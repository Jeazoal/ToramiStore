package com.ToramiStore.ToramiStore.Payloads.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String nombre;
    private String apellidos;
    private String correo;
    private String dni;
    private String numero;
}
