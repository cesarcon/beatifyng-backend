package com.beatifying.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "detalles_factura")
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetallesFactura;

    @NotNull
    private Integer cantidad;
    private double totalDetalle;
    private double iva;

    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Servicio servicio;

   }
