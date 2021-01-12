package org.neoa;

import org.neoa.service.AuthorService;
import org.neoa.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringPersistenceApplication {
    private final AuthorService authorService;
    private final PublisherService publisherService;

    @Autowired
    public SpringPersistenceApplication(AuthorService authorService, PublisherService publisherService) {
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringPersistenceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            authorService.insertAuthors();
            Thread.sleep(3000);
            authorService.newBookOfAuthor();
        };

    }

}
