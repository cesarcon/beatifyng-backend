package com.beatifying.backend.repositories;

import com.beatifying.backend.entities.DetalleFactura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends CrudRepository<CategoriaRepository, Integer> {
}
