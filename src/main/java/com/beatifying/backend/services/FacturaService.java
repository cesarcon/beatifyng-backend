package com.beatifying.backend.services;


import com.beatifying.backend.entities.Factura;
import com.beatifying.backend.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public Factura crearFactura (Factura factura) { return facturaRepository.save(factura);}

    public List<Factura> consultarTodas() {return (List<Factura>) facturaRepository.findAll();}

    public  Factura updateFactura (Factura factura, int idFactura){
        Optional<Factura> optional = facturaRepository.findById(idFactura);
        if (optional.isPresent()){
            Factura nuevaFactura = optional.get();
            nuevaFactura.setPrecio(factura.getPrecio());
            nuevaFactura.setTotalVenta(factura.getTotalVenta());
            nuevaFactura.setFecha(factura.getFecha());
            return facturaRepository.save(nuevaFactura);
        } else {
            return null;
        }
    }

    public void deleteById (int idFactura){
        facturaRepository.deleteById(idFactura);
    }


}
