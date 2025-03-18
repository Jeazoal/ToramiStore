package com.ToramiStore.ToramiStore.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "figura")
public class Figura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "figura_seq")
    @SequenceGenerator(name = "figura_seq", sequenceName = "figura_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "codigo_figura", unique = true, updatable = false, nullable = false)
    private String codigoFigura;  // 🔹 Se debe ingresar manualmente en la petición

    @Column(name = "nombre_figura", nullable = false)
    private String nombreFigura;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "cantidad_inventario", nullable = false)
    private Integer cantidadInventario;

    @Column(name = "altura")
    private Double altura;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "destacado")
    private Boolean destacado;

    @Column(name = "preventa")
    private Boolean preventa;

    @Column(name = "codigo_pedido", unique = true, updatable = false)
    private String codigoPedido;

    // 🔹 Relaciones con otras entidades
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    @JsonIgnore
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_fabricante")
    @JsonIgnore
    private Fabricante fabricante;

    @ManyToOne
    @JoinColumn(name = "id_linea") // Antes era "marca", ahora es "línea"
    @JsonIgnore
    private Linea linea;

    @ManyToOne
    @JoinColumn(name = "id_material")
    @JsonIgnore
    private Material material;

    @ManyToOne
    @JoinColumn(name = "id_tematica")
    @JsonIgnore
    private Tematica tematica;

    @ManyToOne
    @JoinColumn(name = "id_edicion")
    @JsonIgnore
    private Edicion edicion;

    @ManyToOne
    @JoinColumn(name = "id_franquicia")
    @JsonIgnore
    private Franquicia franquicia;

    @JsonBackReference
    @OneToMany(mappedBy = "figura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Venta> ventas;

    @JsonBackReference
    @ManyToMany(mappedBy = "figuras")
    private List<Oferta> ofertas;

    // 🔹 Generar códigos únicos al insertar una figura
    @PrePersist
    @PreUpdate
    public void generateCodigoUnico() {
        if (codigoPedido == null || codigoPedido.isEmpty()) {
            codigoPedido = "PED-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }
}

