package com.ToramiStore.ToramiStore.Payloads.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchFiguraRequest {
    private String nombreFigura;
    private String fabricante;
    private String linea;
    private Double minPrecio;
    private Double maxPrecio;
    private Boolean superPromocion;
}
