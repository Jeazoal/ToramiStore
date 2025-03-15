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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @Column(name = "destacado")
    private Boolean destacado;

    @Column(name = "preventa")
    private Boolean preventa;

    @Column(name = "codigo_pedido", unique = true, updatable = false)
    private String codigoPedido;

    // 🔹 Relaciones con otras entidades con "id_" en los nombres de las claves foráneas
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    @JsonIgnore
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_fabricante")
    @JsonIgnore
    private Fabricante fabricante;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    @JsonIgnore
    private Marca marca;

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

    // 🔹 Generar código único al insertar una figura
    @PrePersist
    public void generateCodigoPedido() {
        if (codigoPedido == null || codigoPedido.isEmpty()) {
            codigoPedido = "PED-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }
}

