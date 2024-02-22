package com.beatifying.backend.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDestacadosDTO {
    private Integer idUsuario;
    private String nombre;
    private Double puntuacion;
    private String foto;
}
