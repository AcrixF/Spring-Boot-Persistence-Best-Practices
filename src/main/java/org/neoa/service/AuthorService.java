package org.neoa.service;

import lombok.extern.log4j.Log4j2;
import org.neoa.entity.Author;
import org.neoa.repository.AuthorRepository;
import org.neoa.repository.AuthorRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class AuthorService {

    private final AuthorRepositoryImpl authorRepository;

    @Autowired
    public AuthorService(AuthorRepositoryImpl repository) {
        this.authorRepository = repository;
    }


    @Transactional
    public void insertAuthor() {
        log.info("Inserting author");
        Author author = new Author();
        author.setName("Joana Nimar");
        author.setGenre("History");
        author.setAge(34);

        authorRepository.save(author);;
    }

    @Transactional
    public void findAuthorByIdViaEntityManager() {
        log.info("Find Author via Entity Manager");
        authorRepository.find(Author.class, 1L)
                .orElseThrow();
    }

    @Transactional
    public void findAuthorByIdViaHibernateSession() {
        log.info("Find Author via Hibernate Session");
        authorRepository.findViaSession(Author.class, 1L);
    }


}


