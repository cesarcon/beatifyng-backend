package com.beatifying.backend.services;


import com.beatifying.backend.dto.DetalleFacturaDTO;
import com.beatifying.backend.dto.FacturaDTO;
import com.beatifying.backend.entities.DetalleFactura;
import com.beatifying.backend.entities.Factura;
import com.beatifying.backend.entities.Servicio;
import com.beatifying.backend.entities.Usuario;
import com.beatifying.backend.repositories.FacturaRepository;
import com.beatifying.backend.repositories.ServicioRepository;
import com.beatifying.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ServicioRepository servicioRepository;

    public Factura crearFactura(FacturaDTO facturaDTO){
        Factura nuevaFactura = Factura.builder()
                .subTotalVenta(facturaDTO.getSubTotalVenta())
                .totalImpuesto(facturaDTO.getTotalImpuesto())
                .totalVenta(facturaDTO.getTotalVenta())
                .codigoFactura(facturaDTO.getCodigoFactura()).build();

        Optional<Usuario> comprador = usuarioRepository.findById(facturaDTO.getComprador());
        Optional<Usuario> vendedor = usuarioRepository.findById(facturaDTO.getVendedor());

        nuevaFactura.setComprador(comprador.get());
        nuevaFactura.setVendedor(vendedor.get());

        List<DetalleFactura> detalleFacturas = new ArrayList<>();
        for (DetalleFacturaDTO detalle: facturaDTO.getDetallesFactura()
             ) {
            DetalleFactura detalleFactura = new DetalleFactura();
            detalleFactura.setCantidad(detalle.getCantidad());
            detalleFactura.setTotalDetalle(detalle.getTotalDetalle());
            detalleFactura.setIva(detalle.getIva());

            Optional<Servicio> servicio = servicioRepository.findById(detalle.getServicio());
            detalleFactura.setServicio(servicio.get());

            detalleFacturas.add(detalleFactura);
        }

        nuevaFactura.setDetallesFactura(detalleFacturas);
        return facturaRepository.save(nuevaFactura);
    }
    public List<Factura> consultarTodas(){
        return (List<Factura>) facturaRepository.findAll();
    }

}
