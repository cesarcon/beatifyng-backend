package com.beatifying.backend.controllers;

import com.beatifying.backend.entities.DetalleFactura;
import com.beatifying.backend.services.Detalles_FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/detalles_factura")
@CrossOrigin("*")
public class Detalles_FacturaController {

    @Autowired
    private Detalles_FacturaService detalles_facturaService;

    @PostMapping
    public ResponseEntity<DetalleFactura> crearDetalles_Factura(@RequestBody DetalleFactura detalle_factura) {
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


}
