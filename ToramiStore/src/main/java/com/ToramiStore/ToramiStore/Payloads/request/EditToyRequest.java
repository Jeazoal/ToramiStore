package com.ToramiStore.ToramiStore.Payloads.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditToyRequest {
    private String nombreFigura;
    private Double precio;
    private Integer cantidadInventario;
    private String linea;
    private String fabricante;
    private Double altura;
    private String distribuidor;
    private String rutaImagen;
}
