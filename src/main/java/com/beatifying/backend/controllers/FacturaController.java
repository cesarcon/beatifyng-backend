package com.beatifying.backend.controllers;

import com.beatifying.backend.entities.Factura;
import com.beatifying.backend.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping (name = "/facturas")
    public ResponseEntity<List<Factura>> pruebaGet () {
        return new ResponseEntity<>(facturaService.consultarFacturas(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Factura> crearFactura(@RequestBody Factura factura){
        return new ResponseEntity<>(facturaService.crearFactura(factura), HttpStatus.CREATED);
    }

    @PutMapping (value = "/update")
    public ResponseEntity<Factura> actualizarFactura(@RequestBody Factura factura, @RequestParam int id) {
        Factura facturaActualizada=facturaService.updateFactura(factura, id);
        if (facturaActualizada == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(facturaActualizada, HttpStatus.OK);
        }
    }

    @DeleteMapping (value = "/{idFactura}")
    public ResponseEntity<Object> deleteById (@PathVariable int idFactura){
        this.facturaService.deleteById(idFactura);
        return ResponseEntity.ok(Boolean.TRUE);
    }


}
