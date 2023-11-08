package com.beatifying.backend.repositories;

import com.beatifying.backend.entities.Puntuacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntuacionesRepository extends CrudRepository<Puntuacion, Integer> {
}
