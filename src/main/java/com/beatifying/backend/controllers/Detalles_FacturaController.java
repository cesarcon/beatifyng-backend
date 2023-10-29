package com.beatifying.backend.controllers;

import com.beatifying.backend.entities.Detalles_Factura;
import com.beatifying.backend.services.Detalles_FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/detalles_factura")
public class Detalles_FacturaController {

    @Autowired
    private Detalles_FacturaService detalles_facturaService;

    @PostMapping
    public ResponseEntity<Detalles_Factura> crearDetalles_Factura(@RequestBody Detalles_Factura detalles_factura) {
        Detalles_Factura generardetalles_factura = detalles_facturaService.guardarDetalles_Factura(detalles_factura);
        return new ResponseEntity<>(generardetalles_factura, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object>updateDetalles_Factura(@RequestBody Detalles_Factura detalles_factura) {
        Detalles_Factura detalles_FacturaUpdate = detalles_facturaService.updateDetalles_Factura(detalles_factura.getIdDetallesFactura());

        if (detalles_FacturaUpdate == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            Detalles_Factura detalles_facturaupdate = detalles_facturaService.guardarDetalles_Factura(detalles_factura);
            return new ResponseEntity<>(detalles_facturaupdate, HttpStatus.OK);
        }
    }


}
