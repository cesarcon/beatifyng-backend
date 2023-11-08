package com.beatifying.backend.controllers;

import com.beatifying.backend.entities.Puntuacion;
import com.beatifying.backend.services.PuntuacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Score")
public class PuntuacionesController {
    @Autowired
    private PuntuacionesService puntuacionesService;

    @GetMapping
    public ResponseEntity<List<Puntuacion>> consultarPuntuaciones() {
        List<Puntuacion> puntuaciones = puntuacionesService.consultarPuntuaciones();
        return new ResponseEntity<>(puntuaciones, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Puntuacion>> guardarPuntuaciones (@RequestBody Puntuacion puntuacion){
      Puntuacion nuevaPuntuacion = puntuacionesService.guardarPuntuaciones(puntuacion);
      List<Puntuacion> list = new ArrayList<>();
      list.add(nuevaPuntuacion);

        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }
}
