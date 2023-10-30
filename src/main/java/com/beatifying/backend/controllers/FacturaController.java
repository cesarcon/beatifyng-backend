package com.beatifying.backend.controllers;

import com.beatifying.backend.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;


}
