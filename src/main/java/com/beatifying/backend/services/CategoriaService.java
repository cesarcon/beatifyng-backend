package com.beatifying.backend.services;

import com.beatifying.backend.entities.Categoria;
import com.beatifying.backend.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> consultarCategorias (){
        return (List<Categoria>) categoriaRepository.findAll();
    }

    public Categoria consultarPorId (Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if(categoria.isPresent()){
            return categoria.get();
        } else {
            return null;
        }
    }
}
