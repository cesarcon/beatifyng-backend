package com.beatifying.backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private LocalDateTime fecha;
    @NotNull
    private double subTotalVenta;
    @NotNull
    private double totalImpuesto;
    @NotNull
    private double totalVenta;
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
