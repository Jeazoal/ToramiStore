package com.ToramiStore.ToramiStore.Payloads.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiguraRequest {
    private String nombreFigura;
    private String fabricante;
    private String linea;
    private String distribuidor;
    private Double precioMin;
    private Double precioMax;
    private Boolean disponible;
    private Boolean superPromocion;
    private Double alturaMin;
    private Double alturaMax;

    public FiguraRequest(String nombreFigura, String fabricante, String linea, String distribuidor,
                         Double precioMin, Double precioMax, Boolean disponible,
                         Boolean superPromocion, Double alturaMin, Double alturaMax) {
        this.nombreFigura = nombreFigura;
        this.fabricante = fabricante;
        this.linea = linea;
        this.distribuidor = distribuidor;
        this.precioMin = precioMin;
        this.precioMax = precioMax;
        this.disponible = disponible;
        this.superPromocion = superPromocion;
        this.alturaMin = alturaMin;
        this.alturaMax = alturaMax;
    }
}
