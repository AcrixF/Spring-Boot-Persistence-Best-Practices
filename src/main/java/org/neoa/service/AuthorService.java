package org.neoa.service;

import lombok.extern.log4j.Log4j2;
import org.neoa.entity.Author;
import org.neoa.entity.Book;
import org.neoa.entity.Publisher;
import org.neoa.repository.AuthorRepository;
import org.neoa.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public AuthorService(AuthorRepository repository, PublisherRepository publisherRepository) {
        this.authorRepository = repository;
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    public void insertAuthors() {
        log.info("Inserting authors");

        Author alicia = new Author()
                .setAge(34)
                .setGenre("Anthology")
                .setName("Alicia Tom");

        Book bookOne = new Book()
                .setTitle("Encyclopedia")
                .setIsbn("001-eI")
                .setPrice(19.90);

        Book bookTwo = new Book()
                .setTitle("Encyclopedia II")
                .setIsbn("001-eII")
                .setPrice(14.99);

        Book bookThree = new Book()
                .setTitle("Encyclopedia III")
                .setIsbn("001-eIII")
                .setPrice(29.99);

        alicia.addBook(bookOne);
        alicia.addBook(bookTwo);
        alicia.addBook(bookThree);

        Author martin = new Author()
                .setAge(50)
                .setGenre("Technology")
                .setName("Martin");

        Book refactoring = new Book()
                .setTitle("Refactoring")
                .setIsbn("001-RFIS-01")
                .setPrice(13.34);

        martin.addBook(refactoring);

        authorRepository.saveAll(List.of(alicia, martin));
    }

    @Transactional(readOnly = true)
    public void fetchAuthorWithAllBooks() {
        log.info("Fetching all author's books");
        Author author = authorRepository.findById(1L).orElseThrow();
        List<Book> books = author.getBooks();
        System.out.println(books);
    }

    @Transactional(readOnly = true)
    public void fetchAuthorWithCheapBooks() {
        log.info("Fetching all the cheap author's books");
        Author author = authorRepository.findById(1L).orElseThrow();
        List<Book> books = author.getCheapBooks();
        System.out.println(books);
    }

    @Transactional(readOnly = true)
    public void fetchAuthorWithRestOfBooks() {
        log.info("Fetching all the rest of the author's books");
        Author author = authorRepository.findById(1L).orElseThrow();
        List<Book> books = author.getRestOfBooks();
        System.out.println(books);
    }
}


