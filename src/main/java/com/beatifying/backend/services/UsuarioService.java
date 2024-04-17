package com.beatifying.backend.services;

import com.beatifying.backend.dto.UsuarioDTO;
import com.beatifying.backend.dto.UsuarioDestacadosDTO;
import com.beatifying.backend.entities.Puntuacion;
import com.beatifying.backend.entities.Role;
import com.beatifying.backend.entities.Usuario;
import com.beatifying.backend.repositories.PuntuacionesRepository;
import com.beatifying.backend.repositories.UsuarioDetailRepository;
import com.beatifying.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioDetailRepository usuarioDetailRepository;

    @Autowired
    private PuntuacionesRepository  puntuacionesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario crearUsuario(Usuario usuario) {
        String password = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(password);
        List<Role> roles = new ArrayList<>();
        roles.add(Role.builder().id(2L).name("ROLE_USER").build());
        if (usuario.getIdTipoUsuario()== 1) {
            roles.add(Role.builder().id(4L).name("ROLE_SELLER").build());
        } else if (usuario.getIdTipoUsuario()== 2) {
            roles.add(Role.builder().id(3L).name("ROLE_BUYER").build());
        }
        usuario.setRoles(roles);
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioDTO> consultarTodos(){
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();

        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for (Usuario user: usuarios) {
            UsuarioDTO userDTO = UsuarioDTO.builder()
                    .idUsuario(user.getIdUsuario())
                    .nombre(user.getNombre())
                    .numeroDocumento(user.getNumeroDocumento())
                    .numeroTelefono(user.getNumeroTelefono())
                    .fechaNacimiento(user.getFechaNacimiento())
                    .genero(user.getGenero())
                    .ciudad(user.getCiudad())
                    .direccion(user.getDireccion())
                    .email(user.getEmail())
                    .idTipoUsuario(user.getIdTipoUsuario())
                    .latitud(user.getLatitud())
                    .longitud(user.getLongitud())
                    .puntuacion(calcularPuntajePromedio(consultarPuntuaciones(user)))
                    .imagenPrincipal(user.getImagenPrincipal())
                    .build();

            usuarioDTOS.add(userDTO);
        }
        return usuarioDTOS;
    }

    public List<Usuario> buscarPorEmailNombre(String email, String nombre){
        return usuarioRepository.findByEmailAndNombre(email, nombre);
    }

    public List<Usuario> buscarPorGenero(String genero){
        return usuarioRepository.findByGenero(genero);
    }

    public void deleteById (Integer idUsuario) {
        usuarioDetailRepository.deleteById(idUsuario);

    }

    public Usuario updateUser(Usuario usuario, int idUsuario) {
        Optional<Usuario> optional = usuarioRepository.findById(idUsuario);
        if (optional.isPresent()) {
            Usuario usuarioActualizado = usuario;
            usuarioActualizado.setPassword(optional.get().getPassword());
            usuarioActualizado.setLatitud(optional.get().getLatitud());
            usuarioActualizado.setLongitud(optional.get().getLongitud());
            return usuarioRepository.save(usuario);
        } else {
            return null;
        }

    }

    public UsuarioDTO login (String numeroDocumento, String password) {
        Optional<Usuario> optional = usuarioRepository.findByNumeroDocumentoAndPassword(numeroDocumento, password);
        if (optional.isPresent()){
            return UsuarioDTO.builder()
                    .nombre(optional.get().getNombre())
                    .idTipoUsuario(optional.get().getIdTipoUsuario())
                    .build();
        } else {
            return null;
        }
    }

    private List<Puntuacion> consultarPuntuaciones (Usuario usuario) {

        return puntuacionesRepository.findAllByCalificado(usuario);
    }

    private Double calcularPuntajePromedio(List<Puntuacion> puntuaciones) {
        if(puntuaciones.size() <1) {
            return 0.0;
        } else {
            Double sumaElementos = 0.0;
            for (Puntuacion puntuacion: puntuaciones) {
                sumaElementos = sumaElementos + puntuacion.getValor();
            }
            return sumaElementos/puntuaciones.size();
        }
    }

    public List<UsuarioDestacadosDTO> usuariosDestacados() {
        List<Map<String, Object>> resultados = usuarioRepository.usuariosDestacados();
        List<UsuarioDestacadosDTO> destacadosDTOS = new ArrayList<>();
        resultados.forEach( x -> {
            UsuarioDestacadosDTO usuarioDestacadosDTO = new UsuarioDestacadosDTO();
            usuarioDestacadosDTO.setIdUsuario((Integer) x.get("idUsuario"));
            usuarioDestacadosDTO.setNombre((String) x.get("nombre"));
            usuarioDestacadosDTO.setPuntuacion((Double) x.get("puntuacion"));
            usuarioDestacadosDTO.setFoto((String) x.get("foto"));
            destacadosDTOS.add(usuarioDestacadosDTO);
                });

        return destacadosDTOS;
    }

    public Usuario buscarUsuarioPorId(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            return null;
        }
    }
}
