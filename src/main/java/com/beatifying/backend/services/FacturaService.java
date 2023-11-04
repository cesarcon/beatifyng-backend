package com.beatifying.backend.services;


import com.beatifying.backend.entities.Factura;
import com.beatifying.backend.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public Factura crearFactura(Factura factura){
        return facturaRepository.save(factura);
    }

}
