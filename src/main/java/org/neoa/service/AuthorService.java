package org.neoa.service;

import lombok.extern.log4j.Log4j2;
import org.neoa.entity.Author;
import org.neoa.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository repository) {
        this.authorRepository = repository;
    }

    @Transactional
    public void insertAuthor() {
        log.info("Inserting Author");

        Author author = new Author();
        author.setName("Joana Nimar");
        author.setGenre("History");
        author.setAge(34);
        authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    public void fetchAuthor() {
        log.info("Fetching Author From Second Cache Level");
        Author author = authorRepository.findById(1L)
                .orElseThrow();
        System.out.println(author);
    }

    @Transactional
    public void updateAuthor() {
        log.debug("Updating Immutable Author");
        Author author = authorRepository.findById(1L)
                .orElseThrow();
        author.setAge(45);
    }

    public void deleteAuthor() {
        log.info("Deleting Immutable Author");
        authorRepository.deleteById(1L);
    }
}


