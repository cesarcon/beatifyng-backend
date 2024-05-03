package com.beatifying.backend.services;

import com.beatifying.backend.dto.ServicioDTO;
import com.beatifying.backend.entities.Categoria;
import com.beatifying.backend.entities.Servicio;
import com.beatifying.backend.entities.Usuario;
import com.beatifying.backend.repositories.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
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

    public List<ServicioDTO> consultarServicios () throws IOException {
        List<Servicio> consultados = (List<Servicio>) servicioRepository.findAll();
        List<ServicioDTO> servicioDTOS = new ArrayList<>();
        for (Servicio s: consultados) {
            servicioDTOS.add(ServicioDTO.builder()
                    .idServicio(s.getIdServicio())
                    .nombre(s.getNombre())
                    .precio(s.getPrecio())
                    .descripcion(s.getDescripcion())
                    .urlImagen(cargarImagen(s.getUrlImagen()))
                    .idCategoria(s.getIdCategoria())
                    .idUsuario(s.getIdUsuario()).build());
        }
        return servicioDTOS;
    }

    private String cargarImagen(String urlImagen) throws IOException {
        Path imagePath = Paths.get(urlImagen);
        byte[] imageBytes = Files.readAllBytes(imagePath);

        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public List<Servicio> consultarServiciosPorUsuario (Integer idUsuario){
        return servicioRepository.findByIdUsuario(idUsuario);
    }

    public List<Servicio> consultarServiciosPorCategoria (Integer idCategoria){
        return servicioRepository.findByIdCategoria(idCategoria);
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
