package com.progcon.programacionconcurrentefinal.spell_management.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpellController {

    /**
     * Endpoint para lanzar un hechizo.
     * Se simula un fallo aleatorio para demostrar el funcionamiento del Circuit Breaker.
     *
     * Ejemplo de URL: http://localhost:8080/cast-spell
     */
    @GetMapping("/cast-spell")
    @CircuitBreaker(name = "spellService", fallbackMethod = "fallbackSpell")
    public String castSpell() {
        // Simulación: falla aleatoriamente para disparar el fallback
        if (Math.random() < 0.5) {
            throw new RuntimeException("Error al lanzar el hechizo");
        }
        return "¡Hechizo lanzado exitosamente!";
    }

    /**
     * Método de fallback que se ejecuta en caso de fallo.
     *
     * @param throwable La excepción que causó el fallo.
     * @return Un mensaje alternativo.
     */
    public String fallbackSpell(Throwable throwable) {
        return "Fallback: No se pudo lanzar el hechizo. Inténtalo nuevamente más tarde.";
    }
}
