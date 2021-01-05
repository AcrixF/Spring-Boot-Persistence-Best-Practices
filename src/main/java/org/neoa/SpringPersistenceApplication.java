package org.neoa;

import org.neoa.appendix.h.FlushMechanism;
import org.neoa.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringPersistenceApplication {
    private final AuthorService authorService;
    private final FlushMechanism flushMechanism;

    @Autowired
    public SpringPersistenceApplication(AuthorService authorService, FlushMechanism flushMechanism) {
        this.authorService = authorService;
        this.flushMechanism = flushMechanism;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringPersistenceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
        };

    }

}
