package com.beatifying.backend.services;

import com.beatifying.backend.entities.Categoria;
import com.beatifying.backend.entities.Servicio;
import com.beatifying.backend.entities.Usuario;
import com.beatifying.backend.repositories.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioService usuarioService;

    public Servicio guardarServicio(Servicio servicio) {
        Categoria categoria = categoriaService.consultarPorId(servicio.getIdCategoria());
        Usuario usuario = usuarioService.buscarUsuarioPorId(servicio.getIdUsuario());
        if (categoria==null || usuario == null) {
            return null;
        } else {
            servicio.setCategoria(categoria);
            servicio.setUsuario(usuario);
            return servicioRepository.save(servicio);
        }

    }

    public List<Servicio> consultarServicios (){
        return (List<Servicio>) servicioRepository.findAll();
    }

    public List<Servicio> consultarServiciosPorUsuario (Integer idUsuario){
        return servicioRepository.findByIdUsuario(idUsuario);
    }

    public Servicio consultarPorId (Integer id) {
        Optional<Servicio> servicio = servicioRepository.findById(id);
        if(servicio.isPresent()){
            return servicio.get();
        } else {
            return null;
        }
    }
    public void borrarPorId (Integer id) {
        servicioRepository.deleteById(id);
    }
}
