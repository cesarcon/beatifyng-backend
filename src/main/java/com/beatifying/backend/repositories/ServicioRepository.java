package com.beatifying.backend.repositories;

import com.beatifying.backend.entities.Servicio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends CrudRepository<Servicio, Integer> {

    List<Servicio> findByIdUsuario(Integer idUsuario);
    List<Servicio> findByIdCategoria(Integer idCategoria);

    @Query(value = "SELECT s.*, " +
            "    (6371 * acos(cos(radians(:lat)) * cos(radians(u.latitud)) " +
                "* cos(radians(u.longitud) - radians(:lon)) + sin(radians(:lat)) " +
                "* sin(radians(u.latitud)))) AS distancia " +
            "FROM servicios s " +
            "JOIN usuarios u ON s.id_usuario = u.id_usuario " +
            "ORDER BY distancia ", nativeQuery = true)
    List<Servicio> ordenadosPorUbicacion(Double lat, Double lon);
    @Query(value = "SELECT s.*, " +
            "    (6371 * acos(cos(radians(:lat)) * cos(radians(u.latitud)) " +
            "* cos(radians(u.longitud) - radians(:lon)) + sin(radians(:lat)) " +
            "* sin(radians(u.latitud)))) AS distancia " +
            "FROM servicios s " +
            "JOIN usuarios u ON s.id_usuario = u.id_usuario " +
            "WHERE s.id_categoria =:idCategoria " +
            "ORDER BY distancia ", nativeQuery = true)
    List<Servicio> porCategoriaOrdenadosPorUbicacion(Integer idCategoria, Double lat, Double lon);
}
