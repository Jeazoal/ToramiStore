package com.ToramiStore.ToramiStore.Payloads.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    private String mensaje;
    private String correo;
    private boolean verificado;

    public RegisterResponse(String mensaje, String correo, boolean verificado) {
        this.mensaje = mensaje;
        this.correo = correo;
        this.verificado = verificado;
    }
}
