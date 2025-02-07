package com.progcon.programacionconcurrentefinal.data_analysis.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class DataProccessingService {

    private ExecutorService executorService;

    @PostConstruct
    public void init() {
        // Creamos un pool de hilos fijo con 10 hilos.
        executorService = Executors.newFixedThreadPool(10);
    }

    @PreDestroy
    public void shutdown() {
        executorService.shutdown();
    }

    /**
     * Procesa una lista de datos de forma concurrente.
     *
     * @param dataList lista de datos a procesar.
     * @return Lista de Future que contendrán los resultados del procesamiento.
     */
    public List<Future<String>> processData(List<String> dataList) {
        List<Future<String>> futures = new ArrayList<>();
        for (String data : dataList) {
            Future<String> future = executorService.submit(() -> {
                // Simulamos un procesamiento que demora 1 segundo.
                Thread.sleep(1000);
                return "Procesado: " + data;
            });
            futures.add(future);
        }
        return futures;
    }
}
