package com.progcon.programacionconcurrentefinal.dinosaur_monitoring.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
public class DinosaurSensorController {

    /**
     * Endpoint que simula el flujo de datos de sensores.
     * Se genera un dato cada segundo con la hora actual.
     *
     * URL de ejemplo: http://localhost:8080/sensors
     *
     * @return Flujo de datos en formato de texto (SSE).
     */
    @GetMapping(value = "/sensors", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamSensorData() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> "Dato del sensor a " + LocalDateTime.now());
    }
}
