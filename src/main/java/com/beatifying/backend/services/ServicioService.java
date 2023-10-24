package com.beatifying.backend.services;

import com.beatifying.backend.entities.Servicio;
import com.beatifying.backend.repositories.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public Servicio guardarServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public List<Servicio> consultarServicios (){
        return (List<Servicio>) servicioRepository.findAll();
    }

    public Servicio consultarPorId (Integer id) {
        Optional<Servicio> servicio = servicioRepository.findById(id);
        if(servicio.isPresent()){
            return servicio.get();
        } else {
            return null;
        }
    }
}
