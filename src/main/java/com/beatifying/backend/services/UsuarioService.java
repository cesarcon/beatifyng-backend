package com.beatifying.backend.services;

import com.beatifying.backend.dto.UsuarioDTO;
import com.beatifying.backend.entities.Puntuacion;
import com.beatifying.backend.entities.Usuario;
import com.beatifying.backend.repositories.PuntuacionesRepository;
import com.beatifying.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PuntuacionesRepository  puntuacionesRepository;

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioDTO> consultarTodos(){
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();

        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for (Usuario user: usuarios) {
            UsuarioDTO userDTO = UsuarioDTO.builder()
                    .idUsuario(user.getIdUsuario())
                    .nombre(user.getNombre())
                    .numeroDocumento(user.getNumeroDocumento())
                    .numeroTelefono(user.getNumeroTelefono())
                    .edad(user.getEdad())
                    .genero(user.getGenero())
                    .ciudad(user.getCiudad())
                    .direccion(user.getDireccion())
                    .email(user.getEmail())
                    .idTipoUsuario(user.getIdTipoUsuario())
                    .latitud(user.getLatitud())
                    .longitud(user.getLongitud())
                    .puntuacion(calcularPuntajePromedio(consultarPuntuaciones(user)))
                    .build();

            usuarioDTOS.add(userDTO);
        }
        return usuarioDTOS;
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

    private List<Puntuacion> consultarPuntuaciones (Usuario usuario) {

        return puntuacionesRepository.findAllByCalificado(usuario);
    }

    private Double calcularPuntajePromedio(List<Puntuacion> puntuaciones) {
        if(puntuaciones.size() <1) {
            return null;
        } else {
            Double sumaElementos = 0.0;
            for (Puntuacion puntuacion: puntuaciones) {
                sumaElementos = sumaElementos + puntuacion.getValor();
            }
            return sumaElementos/puntuaciones.size();
        }
    }


}
