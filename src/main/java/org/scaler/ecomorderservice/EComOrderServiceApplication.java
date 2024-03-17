package org.scaler.ecomorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EComOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EComOrderServiceApplication.class, args);
    }

}
