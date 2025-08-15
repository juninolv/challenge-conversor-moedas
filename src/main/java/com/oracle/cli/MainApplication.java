package com.oracle.cli;

import com.oracle.cli.service.ScreenService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

    @Bean
    public synchronized CommandLineRunner run(ScreenService service) {
        return args -> {
            boolean running = true;

            while (running) {
                running = service.run();
            }
        };
    }
}
