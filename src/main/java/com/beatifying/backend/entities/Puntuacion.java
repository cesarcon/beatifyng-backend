package com.beatifying.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "puntuaciones")
public class Puntuacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPuntuacion;
    private int valor;

    @ManyToOne
    @JoinColumn(name = "id_usuario_calificado")
    private Usuario calificado;

    @ManyToOne
    @JoinColumn(name = "id_usuario_calificador")
    private Usuario calificador;

    public Puntuacion() {
    }

    public Puntuacion(Integer idPuntuacion, int valor, Usuario calificado, Usuario calificador) {
        this.idPuntuacion = idPuntuacion;
        this.valor = valor;
        this.calificado = calificado;
        this.calificador = calificador;
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

    public Usuario getCalificado() {
        return calificado;
    }

    public void setCalificado(Usuario calificado) {
        this.calificado = calificado;
    }

    public Usuario getCalificador() {
        return calificador;
    }

    public void setCalificador(Usuario calificador) {
        this.calificador = calificador;
    }
}
