package com.example.cmabio.controllers;

import com.example.cmabio.model.Cambio;
import com.example.cmabio.repositories.CambioRepository;
import com.example.cmabio.service.CambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("cambio")
public class CambioController {

    @Autowired
    private CambioService cambioService;

    @GetMapping("/{amount}/{from}/{to}")
    public Cambio getCambio(
            @PathVariable BigDecimal amount,
            @PathVariable String from,
            @PathVariable String to) {
        Cambio cambio = cambioService.getFromAndTo(from, to, amount);
        return cambio;
    }
}
