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
    public void insertAuthors() {
        log.info("Inserting Author");
        Author martin = new Author()
                .setName("Martin")
                .setGenre("Tech")
                .setAge(55);

        authorRepository.saveAndFlush(martin);
    }


}


