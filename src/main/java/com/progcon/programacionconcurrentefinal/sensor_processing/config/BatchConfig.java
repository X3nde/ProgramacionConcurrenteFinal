package com.progcon.programacionconcurrentefinal.sensor_processing.config;

import com.progcon.programacionconcurrentefinal.sensor_processing.model.SensorItem;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    /**
     * Bean que define el reader con una lista de datos simulados de sensores.
     */
    @Bean
    public ListItemReader<SensorItem> reader() {
        List<SensorItem> sensorItems = Arrays.asList(
                new SensorItem("sensor1 data"),
                new SensorItem("sensor2 data"),
                new SensorItem("sensor3 data")
        );
        return new ListItemReader<>(sensorItems);
    }

    /**
     * Bean que define el processor. En este ejemplo, se transforma el dato a mayúsculas.
     */
    @Bean
    public ItemProcessor<SensorItem, SensorItem> processor() {
        return sensorItem -> {
            sensorItem.setSensorData(sensorItem.getSensorData().toUpperCase());
            return sensorItem;
        };
    }

    /**
     * Bean que define el writer, que en este caso imprime cada elemento procesado en consola.
     */
    @Bean
    public ItemWriter<SensorItem> writer() {
        return items -> {
            for (SensorItem item : items) {
                System.out.println("Procesado: " + item);
            }
        };
    }

    /**
     * Bean que define el Step del job, donde se configura el chunk size y se asocian el reader, processor y writer.
     */
    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory,
                      ListItemReader<SensorItem> reader,
                      ItemProcessor<SensorItem, SensorItem> processor,
                      ItemWriter<SensorItem> writer) {
        return stepBuilderFactory.get("step1")
                .<SensorItem, SensorItem>chunk(2)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    /**
     * Bean que define el Job, que inicia con el step configurado.
     */
    @Bean
    public Job sensorJob(JobBuilderFactory jobBuilderFactory, Step step1) {
        return jobBuilderFactory.get("sensorJob")
                .start(step1)
                .build();
    }
}
