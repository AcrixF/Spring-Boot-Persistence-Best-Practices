package org.neoa.service;

import lombok.extern.log4j.Log4j2;
import org.neoa.entity.Author;
import org.neoa.entity.Book;
import org.neoa.entity.Publisher;
import org.neoa.repository.AuthorRepository;
import org.neoa.repository.BookRepository;
import org.neoa.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorService(AuthorRepository repository, BookRepository bookRepository) {
        this.authorRepository = repository;
        this.bookRepository = bookRepository;
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
                .setAuthor(alicia);

        Author martin = new Author()
                .setAge(50)
                .setGenre("Technology")
                .setName("Martin");

        Book bookTwo = new Book()
                .setTitle("Refactoring")
                .setIsbn("001-RFIS-01")
                .setAuthor(martin);

        authorRepository.saveAll(List.of(alicia, martin));
    }

    @Transactional
    public void newBookOfAuthor() {
        log.info("Inserting new book");
        Author author = authorRepository.findById(1L).orElseThrow();

        Book book = new Book();
        book.setTitle("AHistory of Ancient Prague");
        book.setIsbn("001-JN");
        book.setAuthor(author);

        bookRepository.save(book);
    }

    @Transactional
    public void fetchingAuthor() {
        log.info("Fetching Author using a bidirectional @OneToOne association");
        authorRepository.findById(1L).orElseThrow();
    }


}


