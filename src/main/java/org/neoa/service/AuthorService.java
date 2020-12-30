package org.neoa.service;

import lombok.extern.log4j.Log4j2;
import org.neoa.entity.Author;
import org.neoa.entity.Book;
import org.neoa.repository.AuthorRepository;
import org.neoa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public void insertAuthor() {
        log.info("Creating Author");
        Author author = new Author()
                .setName("Joana Nimar")
                .setAge(34)
                .setGenre("History");

        authorRepository.saveAndFlush(author);
    }

    @Transactional
    public void addNewBookToAuthor() {
        log.info("Inserting new Book");
        Author author = authorRepository.getOne(1L);

        Book book = new Book()
                .setIsbn("004-JN")
                .setTitle("History Details")
                .setAuthor(author);

        bookRepository.saveAndFlush(book);

        book.setIsbn("not available");
    }

}
