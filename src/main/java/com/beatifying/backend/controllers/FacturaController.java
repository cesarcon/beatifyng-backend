package com.beatifying.backend.controllers;

import com.beatifying.backend.entities.Factura;
import com.beatifying.backend.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping
    public ResponseEntity<Factura> crearFactura(@RequestBody Factura factura){
       Factura nuevaFactura = facturaService.crearFactura(factura);
        return new ResponseEntity<>(nuevaFactura, HttpStatus.CREATED);
    }
}
