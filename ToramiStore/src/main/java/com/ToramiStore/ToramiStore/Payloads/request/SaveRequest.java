package com.ToramiStore.ToramiStore.Payloads.request;

import com.ToramiStore.ToramiStore.Entity.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveRequest {
    private String nombreFigura;
    private Double precio;
    private Integer cantidadInventario;
    private String imagenUrl;
    private String descripcion;

    // Relaciones con otras entidades (llaves foráneas)
    private Categoria categoria;
    private Fabricante fabricante;
    private Marca marca;
    private Material material;
    private Franquicia franquicia;
    private Tematica tematica;
    private Edicion edicion;

    // Nuevos atributos
    private Boolean preventa;
    private Boolean destacado;
}
