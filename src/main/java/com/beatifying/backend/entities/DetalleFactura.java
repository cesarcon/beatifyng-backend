package com.beatifying.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles_factura")
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetallesFactura;
    private int cantidad;
    private double totalDetalle;
    private double iva;


    public DetalleFactura(int idDetallesFactura, int cantidad, double totalDetalle, double iva, int idFactura, int idServicios) {
        this.idDetallesFactura = idDetallesFactura;
        this.cantidad = cantidad;
        this.totalDetalle = totalDetalle;
        this.iva = iva;
    }

    public int getIdDetallesFactura() {
        return idDetallesFactura;
    }

    public void setIdDetallesFactura(int idDetallesFactura) {
        this.idDetallesFactura = idDetallesFactura;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotalDetalle() {
        return totalDetalle;
    }

    public void setTotalDetalle(double totalDetalle) {
        this.totalDetalle = totalDetalle;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

}
