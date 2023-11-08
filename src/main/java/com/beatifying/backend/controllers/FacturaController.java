package com.beatifying.backend.controllers;

import com.beatifying.backend.dto.FacturaDTO;
import com.beatifying.backend.entities.Factura;
import com.beatifying.backend.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

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


    @PostMapping
    public ResponseEntity<Factura> crearFactura(@RequestBody FacturaDTO factura){
       Factura nuevaFactura = facturaService.crearFactura(factura);
        return new ResponseEntity<>(nuevaFactura, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Factura>> consultarTodas(){
        List<Factura> facturas = facturaService.consultarTodas();
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    }
}
