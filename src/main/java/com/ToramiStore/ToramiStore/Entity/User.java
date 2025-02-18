package com.ToramiStore.ToramiStore.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

//uwu
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Transactional
@ToString
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name="id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iduser;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="correo")
    private String correo;
    @Column(name="password")
    private String password;
    @Column(name="dni")
    private int dni;
    @Column(name="departamento")
    private String departamento;
    @Column(name="distrito")
    private String distrito;
    @Column(name="domicilio")
    private String domicilio;
    @Column(name="numero")
    private int numero;
}