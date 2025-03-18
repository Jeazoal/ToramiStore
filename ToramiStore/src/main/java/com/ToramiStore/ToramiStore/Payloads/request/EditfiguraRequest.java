package com.ToramiStore.ToramiStore.Payloads.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditfiguraRequest {

    @NotNull(message = "El ID de la figura es obligatorio")
    private Integer id;

    private String codigoFigura;
    private String nombreFigura;
    private String descripcion;
    private String imagenUrl;
    private Double precio;
    private Integer cantidadInventario;
    private Double altura;
    private Double peso;
    private Boolean destacado;
    private Boolean preventa;

    // Relaciones opcionales
    private Integer categoriaId;
    private Integer fabricanteId;
    private Integer lineaId;
    private Integer materialId;
    private Integer franquiciaId;
    private Integer tematicaId;
    private Integer edicionId;
}
