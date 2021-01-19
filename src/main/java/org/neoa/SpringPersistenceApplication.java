package org.neoa;

import org.neoa.service.AuthorService;
import org.neoa.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringPersistenceApplication {
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public SpringPersistenceApplication(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringPersistenceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            authorService.insertAuthor();
            Thread.sleep(3000);
            authorService.fetchAuthor();
            Thread.sleep(3000);
            authorService.updateAuthor();
            Thread.sleep(3000);
            authorService.fetchAuthor();
            Thread.sleep(3000);
            authorService.deleteAuthor();
        };

    }

}
