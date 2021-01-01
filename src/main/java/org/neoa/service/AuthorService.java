package org.neoa.service;

import org.neoa.entity.Author;
import org.neoa.entity.Book;
import org.neoa.repository.AuthorRepository;
import org.neoa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.log4j.Log4j2;

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

        Author authorAlicia = new Author()
                .setAge(38)
                .setGenre("Anthology")
                .setName("Alicia Tom");

        Author authorMark = new Author()
                .setAge(23)
                .setGenre("Anthology")
                .setName("Mark Janel");

        Book bookOne = new Book()
                .setTitle("The Book of Sword")
                .setIsbn("001-AT-MJ");

        Book bookTwo = new Book()
                .setTitle("One Day")
                .setIsbn("002-AT-MJ");

        Book bookThree = new Book()
                .setTitle("Head Down")
                .setIsbn("001-AT");

        authorAlicia.addBook(bookOne);
        authorAlicia.addBook(bookTwo);
        authorAlicia.addBook(bookThree);

        authorMark.addBook(bookOne);
        authorMark.addBook(bookTwo);

        authorRepository.saveAndFlush(authorAlicia);
        authorRepository.saveAndFlush(authorMark);

        //authorAlicia.removeBook(bookOne);
    }

    @Transactional
    public void deleteBookFromAuthor() {
        log.info("Removing Book from Author");
        Author author = authorRepository.findById(1L).orElseThrow();
        Book book = bookRepository.findById(1L).orElseThrow();

        author.removeBook(book);
    }
}
