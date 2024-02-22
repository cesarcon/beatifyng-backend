package com.beatifying.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    private String genero;
    private String ciudad;
    private String direccion;
    private String email;
    private String password;
    private int idTipoUsuario;
    private double latitud;
    private double longitud;
    @Column(name = "img_ppal")
    private String imagenPrincipal;
}
