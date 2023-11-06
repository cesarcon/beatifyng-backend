package com.beatifying.backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name="facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFactura;
    @JsonFormat(pattern= "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fecha;
    private double subTotalVenta;
    private double totalImpuesto;
    private double totalVenta;
    private Integer codigoFactura;
    @ManyToOne
    @JoinColumn(name = "id_comprador")
    private Usuario comprador;
    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private Usuario vendedor;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_factura")
    private List<DetalleFactura> detallesFactura;

    @PrePersist
    private void generarFecha(){
        this.fecha = LocalDateTime.now();
    }

}
