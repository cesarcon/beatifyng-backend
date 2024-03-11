package com.beatifying.backend.controllers;
import com.beatifying.backend.entities.Categoria;
import com.beatifying.backend.services.CategoriaService;
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
@RequestMapping("/categorias")
@CrossOrigin("*")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> consultarCategorias() {
        List<Categoria> categoria = categoriaService.consultarCategorias();
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearCategoria (@Valid @RequestBody Categoria categoria, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return validation(bindingResult);
        }
        Categoria nuevoCategoria = categoriaService.guardarCategoria(categoria);

        return new ResponseEntity<>(nuevoCategoria, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> actualizarCategoria(@RequestBody Categoria categoria){
        Categoria categoriaConsultado = categoriaService.consultarPorId(categoria.getIdCategoria());

        if (categoriaConsultado == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            Categoria categoriaActualizado = categoriaService.guardarCategoria(categoria);
            return new ResponseEntity<>(categoriaActualizado, HttpStatus.OK);
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
