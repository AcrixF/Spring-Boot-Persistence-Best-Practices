package org.neoa.service;

import lombok.extern.log4j.Log4j2;
import org.neoa.entity.Author;
import org.neoa.entity.Book;
import org.neoa.entity.Publisher;
import org.neoa.repository.AuthorRepository;
import org.neoa.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public AuthorService(AuthorRepository repository, PublisherRepository publisherRepository) {
        this.authorRepository = repository;
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    public void insertAuthors() {
        log.info("Inserting authors");

        Publisher publisherOne = new Publisher()
                .setCompany("Adison");
        Publisher publisherTwo = new Publisher()
                .setCompany("Grijalbo");
        Publisher publisherThree = new Publisher()
                .setCompany("Presley");
        Publisher publisherFour = new Publisher()
                .setCompany("Abison");

        publisherRepository.saveAll(List.of(publisherOne, publisherTwo, publisherThree, publisherFour));


        Author alicia = new Author()
                .setAge(34)
                .setGenre("Anthology")
                .setName("Alicia Tom");

        Book bookOne = new Book()
                .setTitle("Encyclopedia")
                .setIsbn("001-eI")
                .setPublisher(publisherTwo);

        Book bookTwo = new Book()
                .setTitle("Encyclopedia II")
                .setIsbn("001-eII")
                .setPublisher(publisherTwo);

        Book bookThree = new Book()
                .setTitle("Encyclopedia III")
                .setIsbn("001-eIII")
                .setPublisher(publisherTwo);

        alicia.addBook(bookOne);
        alicia.addBook(bookTwo);
        alicia.addBook(bookThree);

        Author martin = new Author()
                .setAge(50)
                .setGenre("Technology")
                .setName("Martin");

        Book refactoring = new Book()
                .setTitle("Refactoring")
                .setIsbn("001-RFIS-01");

        martin.addBook(refactoring);

        authorRepository.saveAll(List.of(alicia, martin));
    }
}


