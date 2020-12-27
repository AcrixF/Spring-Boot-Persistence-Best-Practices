package org.neoa.service;

import org.neoa.entity.AuthorEntity;
import org.neoa.entity.BookEntity;
import org.neoa.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository repository;

    @Autowired
    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void insertAuthorWithBooks() {
        AuthorEntity author = new AuthorEntity()
                .setName("Joana Nimar")
                .setAge(34)
                .setGenre("History");

        BookEntity bookOne = new BookEntity()
                .setIsbn("001-JN")
                .setTitle("A History of Ancient Prague");

        BookEntity bookTwo = new BookEntity()
                .setIsbn("002-JN")
                .setTitle("A People's History");

        BookEntity bookThree = new BookEntity()
                .setIsbn("003-JN")
                .setTitle("World History");

        author.setBooks(List.of(bookOne, bookTwo, bookThree));

        repository.saveAndFlush(author);

    }
}
