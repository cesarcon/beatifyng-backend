package com.beatifying.backend.repositories;

import com.beatifying.backend.entities.UsuarioDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioDetailRepository extends CrudRepository<UsuarioDetail, Integer> {

}
