package com.beatifying.backend.repositories;

import com.beatifying.backend.entities.Puntuacion;
import com.beatifying.backend.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PuntuacionesRepository extends CrudRepository<Puntuacion, Integer> {

    List<Puntuacion> findAllByCalificado(Usuario calificado);
}
