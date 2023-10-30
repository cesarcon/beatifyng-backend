package com.beatifying.backend.repositories;

import com.beatifying.backend.entities.Detalles_Factura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Detalles_FacturaRepository extends CrudRepository<Detalles_Factura, Integer> {
}
