package com.beatifying.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFactura;
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

    public Factura() {

    }

    public Factura(int idFactura, LocalDateTime fecha, double subTotalVenta, double totalImpuesto, double totalVenta, Integer codigoFactura) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.subTotalVenta = subTotalVenta;
        this.totalImpuesto = totalImpuesto;
        this.totalVenta = totalVenta;
        this.codigoFactura = codigoFactura;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getSubTotalVenta() {
        return subTotalVenta;
    }

    public void setSubTotalVenta(double subTotalVenta) {
        this.subTotalVenta = subTotalVenta;
    }

    public double getTotalImpuesto() {
        return totalImpuesto;
    }

    public void setTotalImpuesto(double totalImpuesto) {
        this.totalImpuesto = totalImpuesto;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Integer getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(Integer codigoFactura) {
        this.codigoFactura = codigoFactura;
    }
}
