package com.beatifying.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UbicacionUsuarioDTO {
    @NotNull
    private Integer idUsuario;
    @NotNull
    private Double latitud;
    @NotNull
    private Double longitud;
}
