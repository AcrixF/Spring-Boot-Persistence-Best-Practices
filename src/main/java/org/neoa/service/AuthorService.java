package org.neoa.service;

import lombok.extern.log4j.Log4j2;
import org.neoa.entity.Author;
import org.neoa.entity.Book;
import org.neoa.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Log4j2
public class AuthorService {
    private final AuthorRepository repository;

    @Autowired
    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void insertAuthorWithBooks() {
        log.info("Creating Author");
        Author author = new Author()
                .setName("Joana Nimar")
                .setAge(34)
                .setGenre("History");

        Book bookOne = new Book()
                .setIsbn("001-JN")
                .setTitle("A History of Ancient Prague");

        Book bookTwo = new Book()
                .setIsbn("002-JN")
                .setTitle("A People's History");

        Book bookThree = new Book()
                .setIsbn("003-JN")
                .setTitle("World History");

        author.setBooks(List.of(bookOne, bookTwo, bookThree));

        repository.saveAndFlush(author);
    }

    @Transactional
    public void insertNewBook() {
        log.info("Inserting new Book");
        Author author = repository.findAuthorEntityByName("Joana Nimar")
                .orElseThrow();

        Book book = new Book()
                .setIsbn("004-JN")
                .setTitle("History Details");

        author.addBook(book);
        repository.saveAndFlush(author);
    }
    
    @Transactional
    public void deleteLastBook() {
        log.info("Deleting Last Book");
        Author author = repository.findAuthorEntityByName("Joana Nimar").orElseThrow();
        List<Book> books = author.getBooks();

        author.removeBook(books.get(books.size() - 1));
    }

    @Transactional
    public void deleteFirstBook() {
        log.info("Deleting First Book");
        Author author = repository.findAuthorEntityByName("Joana Nimar").orElseThrow();
        List<Book> books = author.getBooks();

        author.removeBook(books.get(0));
    }
}
