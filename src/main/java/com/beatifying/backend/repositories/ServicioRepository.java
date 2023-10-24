package com.beatifying.backend.repositories;

import com.beatifying.backend.entities.Servicio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends CrudRepository<Servicio, Integer> {
}
