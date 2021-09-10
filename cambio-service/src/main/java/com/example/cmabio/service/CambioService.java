package com.example.cmabio.service;

import com.example.cmabio.model.Cambio;
import com.example.cmabio.repositories.CambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CambioService {

    @Autowired
    private CambioRepository cambioRepository;
    @Autowired
    private Environment environment;

    public Cambio getFromAndTo(String from, String to, BigDecimal amount) {
        var cambio = cambioRepository.findByFromAndTo(from, to);

        if (cambio == null) throw new RuntimeException("Currency Unsupported");

        var conversionFactor = cambio.getConversionFactor();
        var convertedValue = conversionFactor.multiply(amount);
        var port = environment.getProperty("server-port");

        cambio.setConvertedValue(convertedValue);
        cambio.setEnvironment(port);

        return cambio;
    }
}
