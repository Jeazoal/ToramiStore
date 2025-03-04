package com.ToramiStore.ToramiStore.Entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Transactional
@ToString
@Table(name = "toys")
public class Toy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_toy")
    private Integer idToy;

    @Column(name = "nombre_figura")
    private String nombreFigura;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "super_promocion")
    private Boolean superPromocion;

    @Column(name = "cantidad_inventario")
    private Integer cantidadInventario;

    @Column(name = "disponible")
    private Boolean disponible;

    @Column(name = "codigo_pedido", unique = true)
    private String codigoPedido;

    @Column(name = "linea")
    private String linea;

    @Column(name = "fabricante")
    private String fabricante;

    @Column(name = "altura")
    private Double altura;

    @Column(name = "distribuidor")
    private String distribuidor;

    @Column(name = "ruta_imagen")
    private String rutaImagen;

    @PrePersist
    public void generateCodigoPedido() {
        if (this.codigoPedido == null || this.codigoPedido.isEmpty()) {
            this.codigoPedido = "PED-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }

}
