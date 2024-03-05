package com.beatifying.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    private String nombre;
    @NotNull
    private Integer precio;
    @NotBlank
    private String descripcion;
    private String urlImagen;

    @Column(name = "id_categoria", insertable = false, updatable = false)
    private Integer idCategoria;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
}
