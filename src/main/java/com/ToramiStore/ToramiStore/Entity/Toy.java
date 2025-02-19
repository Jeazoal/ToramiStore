package com.ToramiStore.ToramiStore.Entity;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.io.Serializable;

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

    @Column(name = "codigo_producto")
    private String codigoProducto;

    @Column(name = "nombre_figura")
    private String nombreFigura;

    @Column(name = "altura")
    private Double altura;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "ruta_imagen")
    private String rutaImagen;

}
