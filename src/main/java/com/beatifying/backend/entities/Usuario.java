package com.beatifying.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
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

    @NotBlank
    private String nombre;
    @NotEmpty
    @Column(name = "numero_documento")
    private String numeroDocumento;
    private String numeroTelefono;
    @NotNull
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    private String genero;
    private String ciudad;
    private String direccion;
    @Email
    private String email;
    @Size(min = 4)
    @NotBlank
    private String password;
    @NotNull
    private Integer idTipoUsuario;
    private Double latitud;
    private Double longitud;
    @Column(name = "img_ppal")
    private String imagenPrincipal;
}
