package com.ToramiStore.ToramiStore.Payloads.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveResponse {
    private Boolean disponible;
    private String codigoPedido;

    public SaveResponse(Boolean disponible, String codigoPedido){
        this.disponible = disponible;
        this.codigoPedido = codigoPedido;
    }

}
