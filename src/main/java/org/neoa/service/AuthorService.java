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


}
