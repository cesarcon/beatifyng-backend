package com.beatifying.backend.services;

import com.beatifying.backend.entities.Usuario;
import com.beatifying.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> consultarTodos(){
        return (List<Usuario>) usuarioRepository.findAll();
    }

    public List<Usuario> buscarPorEmailNombre(String email, String nombre){
        return usuarioRepository.findByEmailAndNombre(email, nombre);
    }

    public List<Usuario> buscarPorGenero(String genero){
        return usuarioRepository.findByGenero(genero);
    }

    public void deleteById (int idUsuario) {
        usuarioRepository.deleteById(idUsuario);

    }

    public Usuario updateUser(Usuario usuario, int idUsuario) {
        Optional<Usuario> optional = usuarioRepository.findById(idUsuario);
        if (optional.isPresent()) {
            return usuarioRepository.save(usuario);
        } else {
            return null;
        }

    }

    public boolean login (String numeroDocumento, String password) {
        Optional<Usuario> optional = usuarioRepository.findByNumeroDocumentoAndPassword(numeroDocumento, password);

        return optional.isPresent();
    }


}
