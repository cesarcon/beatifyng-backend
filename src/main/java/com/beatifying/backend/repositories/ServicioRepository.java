package com.beatifying.backend.repositories;

import com.beatifying.backend.entities.Servicio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends CrudRepository<Servicio, Integer> {

    List<Servicio> findByIdUsuario(Integer idUsuario);
}
