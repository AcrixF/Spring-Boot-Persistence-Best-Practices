package org.neoa;

import org.neoa.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringPersistenceApplication {
    private final AuthorService authorService;

    @Autowired
    public SpringPersistenceApplication(AuthorService authorService) {
        this.authorService = authorService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringPersistenceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args ->
            authorService.insertAuthorWithBooks();

    }

}
