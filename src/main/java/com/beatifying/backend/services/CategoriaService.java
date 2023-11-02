package com.beatifying.backend.services;

import com.beatifying.backend.entities.Categoria;
import com.beatifying.backend.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria guardarCategoria(Categoria categoria) {
        return CategoriaRepository.save(categoria);
    }

    public List<Categoria> consultarCategorias (){
        return (List<Categoria>) CategoriaRepository.findAll();
    }

    public Categoria consultarPorNombre (String nombre) {
        Optional<Categoria> categoria = CategoriaRepository.findById(String nombre);
        if(categoria.isPresent()){
            return categoria.get();
        } else {
            return null;
        }
    }
}
