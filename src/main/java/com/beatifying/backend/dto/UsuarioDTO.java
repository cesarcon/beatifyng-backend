package com.beatifying.backend.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private Integer idUsuario;
    private String nombre;
    private String numeroDocumento;
    private String numeroTelefono;
    private LocalDate fechaNacimiento;
    private String genero;
    private String ciudad;
    private String direccion;
    private String email;
    private int idTipoUsuario;
    private double latitud;
    private double longitud;
    private double puntuacion;
    private String imagenPrincipal;
}
