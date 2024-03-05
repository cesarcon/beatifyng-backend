package com.beatifying.backend.controllers;

import com.beatifying.backend.entities.DetalleFactura;
import com.beatifying.backend.services.Detalles_FacturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/detalles_factura")
@CrossOrigin("*")
public class Detalles_FacturaController {

    @Autowired
    private Detalles_FacturaService detalles_facturaService;

    @PostMapping
    public ResponseEntity<?> crearDetalles_Factura(@Valid @RequestBody DetalleFactura detalle_factura, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validation(bindingResult);
        }
        DetalleFactura generardetalles_factura = detalles_facturaService.guardarDetalles_Factura(detalle_factura);

        return new ResponseEntity<>(generardetalles_factura, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object>updateDetalles_Factura(@RequestBody DetalleFactura detalle_factura) {
        DetalleFactura detalle_FacturaUpdate = detalles_facturaService.updateDetalles_Factura(detalle_factura.getIdDetallesFactura());

        if (detalle_FacturaUpdate == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            DetalleFactura detalle_facturaupdate = detalles_facturaService.guardarDetalles_Factura(detalle_factura);
            return new ResponseEntity<>(detalle_facturaupdate, HttpStatus.OK);
        }
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
