package com.ToramiStore.ToramiStore.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
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

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "dni", unique = true)
    private String dni;

    @Column(name = "numero", unique = true)
    private String numero;

    @Column(name = "activo", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean activo = false;


    @Column(name = "token")
    private String verificationToken;

    @Column(name = "token_expiration")
    private LocalDateTime tokenExpiration;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void generateVerificationToken() {
        if (this.verificationToken == null || this.tokenExpiration == null || LocalDateTime.now().isAfter(this.tokenExpiration)) {
            this.verificationToken = UUID.randomUUID().toString();
            this.tokenExpiration = LocalDateTime.now().plusMinutes(1);
        }
    }

}

