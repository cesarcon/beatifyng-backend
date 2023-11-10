package com.beatifying.backend.services;

import com.beatifying.backend.dto.PuntuacionDTO;
import com.beatifying.backend.entities.Puntuacion;
import com.beatifying.backend.entities.Usuario;
import com.beatifying.backend.repositories.PuntuacionesRepository;
import com.beatifying.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PuntuacionesService {
    @Autowired
    private PuntuacionesRepository puntuacionesRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Puntuacion guardarPuntuaciones(PuntuacionDTO puntuacionDTO) {
        Optional<Usuario> calificado = usuarioRepository.findById(puntuacionDTO.getCalificado());
        Optional<Usuario> calificador = usuarioRepository.findById(puntuacionDTO.getCalificador());
        Puntuacion puntuacion = new Puntuacion();
        puntuacion.setCalificado(calificado.get());
        puntuacion.setCalificador(calificador.get());
        puntuacion.setValor(puntuacionDTO.getValor());

        return puntuacionesRepository.save(puntuacion);
    }

    public List<Puntuacion> consultarPuntuaciones () { return (List<Puntuacion>) puntuacionesRepository.findAll(); }

}
