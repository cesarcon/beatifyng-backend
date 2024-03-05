package com.beatifying.backend.controllers;

import com.beatifying.backend.dto.PuntuacionDTO;
import com.beatifying.backend.entities.Puntuacion;
import com.beatifying.backend.services.PuntuacionesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Score")
@CrossOrigin("*")
public class PuntuacionesController {
    @Autowired
    private PuntuacionesService puntuacionesService;

    @GetMapping
    public ResponseEntity<List<Puntuacion>> consultarPuntuaciones() {
        List<Puntuacion> puntuaciones = puntuacionesService.consultarPuntuaciones();
        return new ResponseEntity<>(puntuaciones, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> guardarPuntuaciones (@Valid @RequestBody PuntuacionDTO puntuacionDTO, BindingResult result){
        if (result.hasErrors()) {
            return validation(result);
        }

        return new ResponseEntity<>(puntuacionesService.guardarPuntuaciones(puntuacionDTO),HttpStatus.CREATED);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
