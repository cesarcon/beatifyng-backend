package com.beatifying.backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PuntuacionDTO {

    private int valor;

    private Integer calificado;

    private  Integer calificador;
}
