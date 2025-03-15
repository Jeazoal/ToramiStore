package com.ToramiStore.ToramiStore.Payloads.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiguraResponse {
    private String nombreFigura;
    private String fabricante;
    private String linea;
    private String distribuidor;
    private Double precio;
    private Boolean disponible;
    private Boolean superPromocion;
    private Double altura;
    private String codigoPedido;

    public FiguraResponse(String nombreFigura, String fabricante, String linea, String distribuidor,
                          Double precio, Boolean disponible, Boolean superPromocion, Double altura,
                          String codigoPedido) {
        this.nombreFigura = nombreFigura;
        this.fabricante = fabricante;
        this.linea = linea;
        this.distribuidor = distribuidor;
        this.precio = precio;
        this.disponible = disponible;
        this.superPromocion = superPromocion;
        this.altura = altura;
        this.codigoPedido = codigoPedido;
    }
}
