package com.beatifying.backend.controllers;

import com.beatifying.backend.dto.FacturaDTO;
import com.beatifying.backend.entities.Categoria;
import com.beatifying.backend.entities.Factura;
import com.beatifying.backend.entities.Servicio;
import com.beatifying.backend.services.FacturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/factura")
@CrossOrigin("*")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping(value = "/compras/{idUsuario}")
    public ResponseEntity<List<Factura>> consultarComprasPorUsuario(@PathVariable Integer idUsuario) {
        List<Factura> facturas = facturaService.consultarPorComprador(idUsuario);
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    }
    @GetMapping(value = "/ventas/{idUsuario}")
    public ResponseEntity<List<Factura>> consultarVentasPorUsuario(@PathVariable Integer idUsuario) {
        List<Factura> facturas = facturaService.consultarPorVendedor(idUsuario);
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idFactura}")
    public ResponseEntity<Object> deleteById(@PathVariable int idFactura) {
        this.facturaService.deleteById(idFactura);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    @PostMapping
    public ResponseEntity<?> crearFactura(@Valid @RequestBody FacturaDTO factura, BindingResult bindingresult) {
        if (bindingresult.hasErrors()) {
            return validation(bindingresult);
        }
        Factura nuevoFactura = facturaService.crearFactura(factura);
        return new ResponseEntity<>(nuevoFactura, HttpStatus.CREATED);
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}