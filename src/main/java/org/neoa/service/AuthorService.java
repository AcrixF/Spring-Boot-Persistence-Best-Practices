package org.neoa.service;

import lombok.extern.log4j.Log4j2;
import org.neoa.entity.Author;
import org.neoa.entity.Book;
import org.neoa.repository.AuthorRepository;
import org.neoa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .setAge(38)
                .setGenre("Anthology")
                .setName("Alicia Tom");

        Book bookOne = new Book()
                .setTitle("Encyclopedia")
                .setIsbn("001-eI");

        Book bookTwo = new Book()
                .setTitle("Encyclopedia II")
                .setIsbn("001-eII");

        Book bookThree = new Book()
                .setTitle("Encyclopedia III")
                .setIsbn("001-eIII");

        alicia.addBook(bookOne);
        alicia.addBook(bookTwo);
        alicia.addBook(bookThree);

        authorRepository.saveAndFlush(alicia);
    }

    @Transactional
    public void deleteViaCascadeRemove() {
        log.info("Removing author's Books");
        Author author = authorRepository.findById(1L).orElseThrow();
        authorRepository.delete(author);
    }

    @Transactional
    public void deleteViaOrphanRemoval() {
        log.info("Removing author's books by using Orphan Removal");
        Author author = authorRepository.findById(1L).orElseThrow();

        author.removeBooks();
        authorRepository.delete(author);
    }

}


