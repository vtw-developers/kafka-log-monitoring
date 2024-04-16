package com.vtw.restkafkagrafana;

import org.apache.camel.opentelemetry.starter.CamelOpenTelemetry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@CamelOpenTelemetry
@SpringBootApplication
public class RestKafkaGrafanaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestKafkaGrafanaApplication.class, args);
    }

}
