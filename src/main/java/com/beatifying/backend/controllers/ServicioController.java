package com.beatifying.backend.controllers;

import com.beatifying.backend.entities.Servicio;
import com.beatifying.backend.services.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<Servicio>> consultarServicios() {
        List<Servicio> servicios = servicioService.consultarServicios();
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Servicio> crearServicio (@RequestBody Servicio servicio){
        Servicio nuevoServicio = servicioService.guardarServicio(servicio);

        return new ResponseEntity<>(nuevoServicio, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> actualizarServicio(@RequestBody Servicio servicio){
        Servicio servicioConsultado = servicioService.consultarPorId(servicio.getIdServicio());

        if (servicioConsultado == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            Servicio servicioActualizado = servicioService.guardarServicio(servicio);
            return new ResponseEntity<>(servicioActualizado, HttpStatus.OK);
        }
    }


}
