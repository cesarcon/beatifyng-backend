package com.beatifying.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacturaDTO {
    @NotNull
    private Double subTotalVenta;
    @NotNull
    private Double totalImpuesto;
    @NotNull
    private Double totalVenta;
    @NotNull
    private Integer comprador;
    @NotNull
    private Integer vendedor;
    @NotNull
    private List<DetalleFacturaDTO> detallesFactura;
}
