package com.beatifying.backend.repositories;

import com.beatifying.backend.entities.Factura;
import com.beatifying.backend.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FacturaRepository extends CrudRepository<Factura, Integer> {
    List<Factura> findByComprador(Usuario idUsuario);
    List<Factura> findByVendedor(Usuario idUsuario);

}
