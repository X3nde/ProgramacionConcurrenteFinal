package com.progcon.programacionconcurrentefinal.data_analysis.controller;

import com.progcon.programacionconcurrentefinal.data_analysis.service.DataProcessingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@RestController
public class DataAnalysisController {

    private final DataProcessingService dataProcessingService;

    public DataAnalysisController(DataProcessingService dataProcessingService) {
        this.dataProcessingService = dataProcessingService;
    }

    /**
     * Endpoint para procesar un conjunto de datos.
     * Ejemplo de URL: http://localhost:8080/data-analysis/process
     *
     * @return Lista de resultados del procesamiento.
     */
    @GetMapping("/data-analysis/process")
    public ResponseEntity<List<String>> processData() {
        try {
            // Datos de ejemplo a procesar.
            List<String> data = Arrays.asList("dato1", "dato2", "dato3", "dato4");
            List<Future<String>> futures = dataProcessingService.processData(data);
            // Recolectamos los resultados de cada Future.
            List<String> results = futures.stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            return "Error procesando: " + e.getMessage();
                        }
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
