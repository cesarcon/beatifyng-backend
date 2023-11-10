package com.beatifying.backend.controllers;

import com.beatifying.backend.dto.PuntuacionDTO;
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
    public ResponseEntity<Puntuacion> guardarPuntuaciones (@RequestBody PuntuacionDTO puntuacionDTO){

        return new ResponseEntity<>(puntuacionesService.guardarPuntuaciones(puntuacionDTO),HttpStatus.CREATED);
    }
}
