package com.beatifying.backend.repositories;

import com.beatifying.backend.dto.UsuarioDTO;
import com.beatifying.backend.dto.UsuarioDestacadosDTO;
import com.beatifying.backend.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    public List<Usuario> findByEmail(String email);

    public List<Usuario> findByEmailAndNombre(String email, String nombre);

    public List<Usuario> findByGenero(String genero);

    public Optional<Usuario> findByNumeroDocumentoAndPassword (String numeroDocumento, String password);

    @Query(nativeQuery = true,
    value = "SELECT us.id_usuario as idUsuario, us.nombre as nombre, avg(usp.valor) puntuacion, us.img_ppal as foto" +
            " FROM beautifyngsoft.usuarios us " +
            "inner join beautifyngsoft.puntuaciones usp where us.id_usuario = usp.id_usuario_calificado " +
            "and us.id_tipo_usuario = 1 " +
            "group by us.id_usuario " +
            "order by puntuacion desc " +
            "limit 10;")
    public List<Map<String, Object>> usuariosDestacados();
}
