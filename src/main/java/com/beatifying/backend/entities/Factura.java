package com.beatifying.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name="facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFactura;
    private double totalVenta;
    private int fecha;
    private double precio;

    public Factura() {
    }

    public Factura(int idFactura, double totalVenta, int fecha, double precio) {
        this.idFactura = idFactura;
        this.totalVenta = totalVenta;
        this.fecha = fecha;
        this.precio = precio;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
