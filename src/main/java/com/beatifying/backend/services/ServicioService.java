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

    public List<ServicioDTO> consultarServicios () {
        List<Servicio> consultados = (List<Servicio>) servicioRepository.findAll();
        List<ServicioDTO> servicioDTOS = new ArrayList<>();
        for (Servicio s: consultados) {
            servicioDTOS.add(convertirADto(s));
        }
        return servicioDTOS;
    }
    public List<ServicioDTO> serviciosOrdenadosPorUbicacion (Double lat, Double lon) {
        List<Servicio> consultados = servicioRepository.ordenadosPorUbicacion(lat, lon);
        List<ServicioDTO> servicioDTOS = new ArrayList<>();
        for (Servicio s: consultados) {
            servicioDTOS.add(convertirADto(s));
        }
        return servicioDTOS;
    }

    private String cargarImagen(String urlImagen) {
        try {
            Path imagePath = Paths.get(urlImagen);
            byte[] imageBytes = Files.readAllBytes(imagePath);

            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            System.out.println(e);
            return "";
        }

    }

    public List<ServicioDTO> consultarServiciosPorUsuario (Integer idUsuario){
        List<Servicio> servicios = servicioRepository.findByIdUsuario(idUsuario);
        List<ServicioDTO> servicioDTOS = new ArrayList<>();
        for (Servicio s: servicios) {
            servicioDTOS.add(convertirADto(s));
        }
        return servicioDTOS;
    }

    public List<ServicioDTO> consultarServiciosPorCategoria (Integer idCategoria) {
        List<Servicio> consultados =  servicioRepository.findByIdCategoria(idCategoria);
        List<ServicioDTO> servicioDTOS = new ArrayList<>();
        for (Servicio s: consultados) {
            servicioDTOS.add(convertirADto(s));
        }
        return servicioDTOS;
    }

    public List<ServicioDTO> serviciosPorCategoriaOrder (Integer idCategoria, Double lat, Double lon) {
        List<Servicio> consultados =  servicioRepository
                .porCategoriaOrdenadosPorUbicacion(idCategoria, lat, lon);
        List<ServicioDTO> servicioDTOS = new ArrayList<>();
        for (Servicio s: consultados) {
            servicioDTOS.add(convertirADto(s));
        }
        return servicioDTOS;
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

    private ServicioDTO convertirADto (Servicio s) {
        return ServicioDTO.builder()
                .idServicio(s.getIdServicio())
                .nombre(s.getNombre())
                .precio(s.getPrecio())
                .descripcion(s.getDescripcion())
                .urlImagen(cargarImagen(s.getUrlImagen()))
                .idCategoria(s.getIdCategoria())
                .idUsuario(s.getIdUsuario())
                .usuario(s.getUsuario().getNombre()).build();
    }
}
