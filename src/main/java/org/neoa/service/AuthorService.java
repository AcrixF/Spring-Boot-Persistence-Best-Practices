package org.neoa.service;

import lombok.extern.log4j.Log4j2;
import org.neoa.entity.Author;
import org.neoa.entity.Book;
import org.neoa.repository.AuthorRepository;
import org.neoa.repository.BookRepository;
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

        Book book = new Book()
                .setTitle("Encyclopedia")
                .setIsbn("001-all");

        Author authorAlicia = new Author()
                .setAge(38)
                .setGenre("Anthology")
                .setName("Alicia Tom");

        authorAlicia.addBook(book);

        Author authorMark = new Author()
                .setAge(23)
                .setGenre("Anthology")
                .setName("Mark Janel");
        authorMark.addBook(book);

        Author authorQuartis = new Author()
                .setAge(51)
                .setGenre("Anthology")
                .setName("Quartis Young");
        authorQuartis.addBook(book);

        Author authorKaty = new Author()
                .setAge(56)
                .setGenre("Anthology")
                .setName("Katy Loin");
        authorKaty.addBook(book);

        Author authorMartin = new Author()
                .setAge(38)
                .setGenre("Anthology")
                .setName("Martin Leon");
        authorMartin.addBook(book);

        Author authorQart = new Author()
                .setAge(56)
                .setGenre("Anthology")
                .setName("Qart Pinkil");
        authorQart.addBook(book);

        authorRepository.saveAll(List.of(authorAlicia, authorKaty, authorMark, authorQart, authorMartin, authorQuartis));
    }

    @Transactional
    public void fetchAllBookAuthors() {
        Book book = bookRepository.findById(1L).orElseThrow();
        book.getAuthors().forEach(System.out::println);
    }
}


