package com.beatifying.backend.controllers;

import com.beatifying.backend.dto.Login;
import com.beatifying.backend.dto.UbicacionUsuarioDTO;
import com.beatifying.backend.dto.UsuarioDTO;
import com.beatifying.backend.dto.UsuarioDestacadosDTO;
import com.beatifying.backend.entities.Usuario;
import com.beatifying.backend.services.UsuarioService;
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
@RequestMapping(value = "/users")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> consultarTodos() {
        return new ResponseEntity<>(usuarioService.consultarTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario, BindingResult result){
        if (result.hasErrors()) {
            return validation(result);
        }
        return new ResponseEntity<>(usuarioService.crearUsuario(usuario), HttpStatus.CREATED);
    }
    @GetMapping("/consultar-por-email/{email}/{nombre}")
    public ResponseEntity<List<Usuario>> consultarPorEmailNombre(@PathVariable String email,
                                                                 @PathVariable String nombre){
        return new ResponseEntity<> (usuarioService.buscarPorEmailNombre(email, nombre), HttpStatus.OK);
    }

    @GetMapping("/consultar-por-genero/{genero}")
    public ResponseEntity<List<Usuario>> consultarPorGenero(@PathVariable String genero){
        return new ResponseEntity<> (usuarioService.buscarPorGenero(genero), HttpStatus.OK);
    }

    @DeleteMapping (value="/{idUsuario}/delete")
    public ResponseEntity<Object> deleteById (@PathVariable Integer idUsuario){
        this.usuarioService.deleteById(idUsuario);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PutMapping (value= "/update")
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody Usuario usuario, @RequestParam int id){
        Usuario usuarioActualizado = usuarioService.updateUser(usuario, id);
        if (usuarioActualizado == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        }

    }

    @PostMapping (value = "/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody Login login) {
        System.out.println("EN login");
        UsuarioDTO user = usuarioService.login(login.getNumeroDocumento(), login.getPassword());

        if (user !=null) {

            return new ResponseEntity<>(usuarioService.login(login.getNumeroDocumento(), login.getPassword()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(user, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping (value = "/destacados")
    public ResponseEntity<List<UsuarioDestacadosDTO>> usuariosDestacados() {
        return new ResponseEntity<>(usuarioService.usuariosDestacados(), HttpStatus.OK);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @PutMapping (value= "/location")
    public ResponseEntity<?> actualizarUbicacion(@Valid @RequestBody UbicacionUsuarioDTO usuario,
                                                      BindingResult result){
        if (result.hasErrors()) {
            return validation(result);
        }
        Usuario usuarioActualizado = usuarioService.updateLocationUser(usuario);
        if (usuarioActualizado == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }

    }
}
