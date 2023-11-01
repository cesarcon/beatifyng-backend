package com.beatifying.backend.controllers;

import com.beatifying.backend.entities.Puntuaciones;
import com.beatifying.backend.services.PuntuacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(*/Score*)
public class PuntuacionesController {
    @Autowired
    private PuntuacionesService puntuacionesService;

    @GetMapping
    public ResponseEntity<List<Puntuaciones>> consultarPuntuaciones() {
        List<Puntuaciones> puntuaciones = puntuacionesService.consultarPuntuaciones();
        return new ResponseEntity<>(puntuaciones, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Puntuaciones>> guardarPuntuaciones (@RequestBody Puntuaciones puntuaciones){
      Puntuaciones nuevaPuntuaciones = puntuacionesService.guardarPuntuaciones(puntuaciones);

        return new ResponseEntity<>(nuevaPuntuaciones, HttpStatus.CREATED);
    }
}
