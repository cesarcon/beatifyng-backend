package com.beatifying.backend.controllers;
import com.beatifying.backend.entities.Categoria;
import com.beatifying.backend.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> consultarCategorias() {
        List<Categoria> categoria = categoriaService.consultarCategorias();
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Categoria> crearCategoria (@RequestBody Categoria categoria){
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



}
