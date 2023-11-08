package com.beatifying.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String nombre;
    @Column(name = "numero_documento")
    private String numeroDocumento;
    private String numeroTelefono;
    private int edad;
    private String genero;
    private String ciudad;
    private String direccion;
    private String email;
    private String password;
    private int idTipoUsuario;
    private double latitud;
    private double longitud;

    @OneToMany
    @JoinColumn(name = "id_usuario_calificado")
    private List<Puntuacion> puntuaciones;

}
