package com.beatifying.backend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleFacturaDTO {

    private int cantidad;
    private double totalDetalle;
    private double iva;
    private Integer servicio;
}
