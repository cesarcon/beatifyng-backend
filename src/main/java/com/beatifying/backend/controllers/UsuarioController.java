package com.beatifying.backend.controllers;

import com.beatifying.backend.dto.Login;
import com.beatifying.backend.entities.Usuario;
import com.beatifying.backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> pruebaGet() {
        return new ResponseEntity<>(usuarioService.consultarTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
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
    public ResponseEntity<Object> deleteById (@PathVariable int idUsuario){
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
    public ResponseEntity<String> login(@RequestBody Login login) {
        boolean esValido = usuarioService.login(login.getNumeroDocumento(), login.getPassword());

        if (esValido) {
            return new ResponseEntity<>("Usuario Autenticado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Credenciales invalidas", HttpStatus.FORBIDDEN);
        }
    }
}
