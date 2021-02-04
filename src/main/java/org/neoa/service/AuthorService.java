package org.neoa.service;

import lombok.extern.log4j.Log4j2;
import org.neoa.entity.Author;
import org.neoa.repository.DaoImpl;
import org.neoa.repository.specifications.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class AuthorService {

    private final DaoImpl daoImpl;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(DaoImpl daoImpl, AuthorRepository authorRepository) {
        this.daoImpl = daoImpl;
        this.authorRepository = authorRepository;
    }


    @Transactional
    public void insertAuthor() {
        log.info("Inserting author");
        Author author = new Author();
        author.setName("Joana Nimar");
        author.setGenre("History");
        author.setAge(34);

        daoImpl.save(author);;
    }

    @Transactional
    public void directFetching() {
        log.info("Direct fetching via Spring Data");
        authorRepository.findById(1L);
        log.info("Direct fetching via EntityManager");
        daoImpl.find(Author.class, 1L);
        log.info("Direct fetching via Session");
        daoImpl.findViaSession(Author.class, 1L);
    }


}


