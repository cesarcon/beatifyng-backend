package com.beatifying.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "puntuaciones")
public class Puntuaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPuntuacion;
    private int valor;
    private int idUsuarioCalificado;
    private int idUsuarioCalificador;

    public Puntuaciones() {
    }

    public Puntuaciones(Integer idPuntuacion, int valor, int idUsuarioCalificado, int idUsuarioCalificador) {
        this.idPuntuacion = idPuntuacion;
        this.valor = valor;
        this.idUsuarioCalificado = idUsuarioCalificado;
        this.idUsuarioCalificador = idUsuarioCalificador;
    }

    public Integer getIdPuntuacion() {
        return idPuntuacion;
    }

    public void setIdPuntuacion(Integer idPuntuacion) {
        this.idPuntuacion = idPuntuacion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getIdUsuarioCalificado() {
        return idUsuarioCalificado;
    }

    public void setIdUsuarioCalificado(int idUsuarioCalificado) {
        this.idUsuarioCalificado = idUsuarioCalificado;
    }

    public int getIdUsuarioCalificador() {
        return idUsuarioCalificador;
    }

    public void setIdUsuarioCalificador(int idUsuarioCalificador) {
        this.idUsuarioCalificador = idUsuarioCalificador;
    }
}
