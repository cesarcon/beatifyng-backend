package com.beatifying.backend.services;

import com.beatifying.backend.entities.Puntuacion;
import com.beatifying.backend.repositories.PuntuacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuntuacionesService {
    @Autowired
    private PuntuacionesRepository puntuacionesRepository;

    public Puntuacion guardarPuntuaciones(Puntuacion puntuacion) {
        return puntuacionesRepository.save(puntuacion);
    }

    public List<Puntuacion> consultarPuntuaciones () { return (List<Puntuacion>) puntuacionesRepository.findAll(); }

}
