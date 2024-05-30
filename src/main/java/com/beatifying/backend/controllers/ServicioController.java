package com.beatifying.backend.controllers;

import com.beatifying.backend.dto.ServicioDTO;
import com.beatifying.backend.entities.Servicio;
import com.beatifying.backend.services.ServicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/services")
@CrossOrigin("*")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<ServicioDTO>> consultarServicios(
            @RequestParam(required = false) Double lat, @RequestParam(required = false) Double lon)
            {
        if (Objects.isNull(lat) || Objects.isNull(lon)) {
            List<ServicioDTO> servicios = servicioService.consultarServicios();
            return new ResponseEntity<>(servicios, HttpStatus.OK);
        } else {
            List<ServicioDTO> servicios = servicioService.serviciosOrdenadosPorUbicacion(lat, lon);
            return new ResponseEntity<>(servicios, HttpStatus.OK);
        }

    }

    @GetMapping(value = "/{idUsuario}")
    public ResponseEntity<List<ServicioDTO>> consultarServiciosPorUsuario(@PathVariable Integer idUsuario) {
        List<ServicioDTO> servicios = servicioService.consultarServiciosPorUsuario(idUsuario);
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }

    @GetMapping(value = "/categoria/{id}")
    public ResponseEntity<List<ServicioDTO>> consultarServiciosPorCategoria(@PathVariable Integer id,
                 @RequestParam(required = false) Double lat, @RequestParam(required = false) Double lon) {
        if (Objects.isNull(lat) || Objects.isNull(lon)) {
            List<ServicioDTO> servicios = servicioService.consultarServiciosPorCategoria(id);
            return new ResponseEntity<>(servicios, HttpStatus.OK);
        } else {
            List<ServicioDTO> servicios = servicioService.serviciosPorCategoriaOrder(id, lat, lon);
            return new ResponseEntity<>(servicios, HttpStatus.OK);
        }

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
        return guardarImagen(servicio, true);
    }

    @PutMapping
    public ResponseEntity<?> actualizarServicio(@RequestBody Servicio servicio){
        Servicio servicioConsultado = servicioService.consultarPorId(servicio.getIdServicio());
        if (servicioConsultado == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            if (servicio.getUrlImagen() == null) {
                servicio.setUrlImagen(servicioConsultado.getUrlImagen());
                Servicio servicioActualizado = servicioService.guardarServicio(servicio);
                return new ResponseEntity<>(servicioActualizado, HttpStatus.OK);
            } else {
                return guardarImagen(servicio, false);
            }
        }
    }

    private ResponseEntity<?> guardarImagen(Servicio servicio, Boolean crear) {
        String base64Image = servicio.getUrlImagen();
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        servicio.setUrlImagen("");
        Servicio nuevoServicio = servicioService.guardarServicio(servicio);
        if (Objects.nonNull(nuevoServicio)) {
            StringBuilder fileName = new StringBuilder();
            fileName.append("imagen_service");
            fileName.append(nuevoServicio.getIdServicio());
            fileName.append(".jpg");
            String uploadDirectory = "./src/main/resources/images/";
            String path = uploadDirectory.concat(fileName.toString());
            try (FileOutputStream fos = new FileOutputStream(new File(path))) {
                fos.write(imageBytes);
                nuevoServicio.setUrlImagen(path);
                servicio = servicioService.guardarServicio(nuevoServicio);
                return new ResponseEntity<>(servicio,
                        crear ? HttpStatus.CREATED : HttpStatus.OK);
            } catch (IOException e) {
                servicioService.borrarPorId(nuevoServicio.getIdServicio());
                return new ResponseEntity<>("Error al guardar la imagen: " + e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Error al guardar el servicio", HttpStatus.BAD_REQUEST);
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
