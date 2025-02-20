package com.ToramiStore.ToramiStore.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "correo", unique = true, nullable = false)
    private String correo;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "dni", unique = true)
    private String dni;

    @Column(name = "numero", unique = true)
    private String numero;

    @Column(name = "activo")
    private Boolean activo = false;

    @Column(name = "token")
    private String verificationToken;

    public void generateVerificationToken() {
        this.verificationToken = UUID.randomUUID().toString();
    }
}

