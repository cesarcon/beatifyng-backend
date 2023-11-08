package com.beatifying.backend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacturaDTO {
    private double subTotalVenta;
    private double totalImpuesto;
    private double totalVenta;
    private Integer codigoFactura;
    private Integer comprador;
    private Integer vendedor;
    private List<DetalleFacturaDTO> detallesFactura;
}
