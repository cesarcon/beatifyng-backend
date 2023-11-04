package com.beatifying.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detalles_factura")
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetallesFactura;
    private int cantidad;
    private double totalDetalle;
    private double iva;

    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Servicio servicio;

   }
