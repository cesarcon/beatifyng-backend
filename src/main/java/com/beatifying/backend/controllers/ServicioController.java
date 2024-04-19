package com.beatifying.backend.controllers;

import com.beatifying.backend.entities.Servicio;
import com.beatifying.backend.services.ServicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/services")
@CrossOrigin("*")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<Servicio>> consultarServicios() {
        List<Servicio> servicios = servicioService.consultarServicios();
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }

    @GetMapping(value = "/{idUsuario}")
    public ResponseEntity<List<Servicio>> consultarServiciosPorUsuario(@PathVariable Integer idUsuario) {
        List<Servicio> servicios = servicioService.consultarServiciosPorUsuario(idUsuario);
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }

    @GetMapping(value = "/categoria/{id}")
    public ResponseEntity<List<Servicio>> consultarServiciosPorCategoria(@PathVariable Integer id) {
        List<Servicio> servicios = servicioService.consultarServiciosPorCategoria(id);
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }
    @DeleteMapping(value = "/{idService}")
    public ResponseEntity<Object> borrarPorId(@PathVariable Integer idService) {
        servicioService.borrarPorId(idService);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PostMapping
    public ResponseEntity<?> crearServicio (@Valid @RequestBody Servicio servicio, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return validation(bindingResult);
        }
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

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
