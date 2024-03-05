package com.beatifying.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PuntuacionDTO {

    @NotNull
    private Integer valor;
    @NotNull
    private Integer calificado;
    @NotNull
    private  Integer calificador;
}
