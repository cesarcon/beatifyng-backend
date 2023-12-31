package com.beatifying.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idServicio;
    private String nombre;
    private Float precio;
    private String descripcion;
    private String urlImagen;

    @Column(name = "id_categoria", insertable = false, updatable = false)
    private Integer idCategoria;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
}
