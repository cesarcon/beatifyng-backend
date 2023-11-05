package com.beatifying.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    }
