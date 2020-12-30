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
        Author authorAlicia = new Author()
                .setAge(38)
                .setGenre("Anthology")
                .setName("Alicia Tom");

        Author authorMark = new Author()
                .setAge(23)
                .setGenre("Anthology")
                .setName("Mark Janel");

        authorRepository.saveAll(List.of(authorAlicia, authorMark));
    }

    @Transactional
    public void insertBooks() throws InterruptedException {
        log.info("Inserting books");
        Book bookOne = new Book()
                .setTitle("The Book od Sword")
                .setIsbn("001-AT-MJ");

        Book bookTwo = new Book()
                .setTitle("One Day")
                .setIsbn("002-AT-MJ");

        Book bookThree = new Book()
                .setTitle("Head Down")
                .setIsbn("001-AT");

        bookRepository.saveAll(List.of(bookOne, bookTwo, bookThree));

        Author authorOne = authorRepository.getOne(1L);

        log.info("Saving Book in Author One");
        authorOne.setBooks(List.of(bookOne, bookTwo, bookThree));
        Thread.sleep(3000);

        log.info("Saving Book in Author Two");
        Author authorTwo = authorRepository.getOne(2L);
        authorTwo.setBooks(List.of(bookOne, bookTwo));
    }


}
