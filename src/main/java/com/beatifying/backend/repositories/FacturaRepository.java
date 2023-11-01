package com.beatifying.backend.repositories;

import com.beatifying.backend.entities.Factura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface FacturaRepository extends CrudRepository<Factura, Integer> {

}
