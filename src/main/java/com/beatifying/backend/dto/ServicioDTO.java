package com.beatifying.backend.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ServicioDTO {
    private Integer idServicio;
    private String nombre;
    private Integer precio;
    private String descripcion;
    private String urlImagen;
    private Integer idCategoria;
    private Integer idUsuario;
}
