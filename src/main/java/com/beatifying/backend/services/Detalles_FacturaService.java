package com.beatifying.backend.services;

import com.beatifying.backend.entities.Detalles_Factura;
import com.beatifying.backend.repositories.Detalles_FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Detalles_FacturaService {

    @Autowired
    private Detalles_FacturaRepository detalles_facturaRepository;

    public Detalles_Factura guardarDetalles_Factura(Detalles_Factura detalles_factura) {
        return detalles_facturaRepository.save(detalles_factura);
    }

    public Detalles_Factura updateDetalles_Factura(int idDetalles_Factura) {
        Optional<Detalles_Factura> optional = detalles_facturaRepository.findById(idDetalles_Factura);
        if (optional.isPresent()) {
            return null;
        } else {
            return null;
        }
    }


}
