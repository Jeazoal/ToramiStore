package com.ToramiStore.ToramiStore.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer idUser;
    private String nombre;
    private String apellidos;
    private String correo;
    private String dni;
    private String numero;
}

