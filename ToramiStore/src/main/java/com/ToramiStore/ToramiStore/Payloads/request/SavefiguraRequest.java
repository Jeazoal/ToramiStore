package com.ToramiStore.ToramiStore.Payloads.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavefiguraRequest {
    private String codigoFigura; // 📌 Código único de la figura
    private String codigoPedido; // 📌 Código único del pedido (nuevo)
    private String nombreFigura;
    private Double precio;
    private Integer cantidadInventario;
    private String imagenUrl;
    private String descripcion;

    // ✅ Relaciones con otras entidades (ahora con IDs en lugar de objetos)
    private Integer categoriaId;
    private Integer fabricanteId;
    private Integer lineaId;
    private Integer materialId;
    private Integer franquiciaId;
    private Integer tematicaId;
    private Integer edicionId;

    // ✅ Nuevos atributos
    private Boolean preventa;
    private Boolean destacado;
    private Double altura; // 📌 Nuevo atributo agregado
    private Double peso; // 📌 Nuevo atributo agregado
}
