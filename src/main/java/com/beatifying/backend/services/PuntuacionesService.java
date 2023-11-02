package com.beatifying.backend.services;

import com.beatifying.backend.entities.Puntuaciones;
import com.beatifying.backend.repositories.PuntuacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuntuacionesService {
    @Autowired
    private PuntuacionesRepository puntuacionesRepository;

    public Puntuaciones guardarPuntuaciones(Puntuaciones puntuaciones) {
        return puntuacionesRepository.save(puntuaciones);
    }

    public List<Puntuaciones> consultarPuntuaciones () { return (List<Puntuaciones>) puntuacionesRepository.findAll(); }

}
