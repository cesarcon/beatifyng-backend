package com.beatifying.backend.repositories;

import com.beatifying.backend.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    public List<Usuario> findByEmail(String email);

    public List<Usuario> findByEmailAndNombre(String email, String nombre);

    public List<Usuario> findByGenero(String genero);

    public Optional<Usuario> findByNumeroDocumentoAndPassword (String numeroDocumento, String password);
}
