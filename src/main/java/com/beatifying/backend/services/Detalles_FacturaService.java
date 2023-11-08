package com.beatifying.backend.services;

import com.beatifying.backend.entities.DetalleFactura;
import com.beatifying.backend.repositories.Detalles_FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Detalles_FacturaService {

    @Autowired
    private Detalles_FacturaRepository detalles_facturaRepository;

    public DetalleFactura guardarDetalles_Factura(DetalleFactura detalle_factura) {
        return detalles_facturaRepository.save(detalle_factura);
    }

    public DetalleFactura updateDetalles_Factura(int idDetalles_Factura) {
        Optional<DetalleFactura> optional = detalles_facturaRepository.findById(idDetalles_Factura);
        if (optional.isPresent()) {
            return null;
        } else {
            return null;
        }
    }


}
