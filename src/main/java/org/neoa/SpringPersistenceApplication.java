package org.neoa;

import org.neoa.entity.Author;
import org.neoa.entity.Book;
import org.neoa.repository.AuthorRepository;
import org.neoa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringPersistenceApplication {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public SpringPersistenceApplication(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringPersistenceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return (args) -> {
            Author author = new Author()
                    .setName("Martin Fowler")
                    .setAge(50)
                    .setGenre("Programming");

            Book book = new Book()
                    .setIsbn("978-0-13-475759-9")
                    .setAuthor(author)
                    .setTitle("Refactoring Improving the Design of Existing Code");

            author.addBook(book);

            authorRepository.saveAndFlush(author);
        };
    }
}
