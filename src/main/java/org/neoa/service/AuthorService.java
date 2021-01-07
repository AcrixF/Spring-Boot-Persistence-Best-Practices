package org.neoa.service;

import lombok.extern.log4j.Log4j2;
import org.neoa.entity.Author;
import org.neoa.entity.Book;
import org.neoa.entity.Publisher;
import org.neoa.repository.AuthorRepository;
import org.neoa.repository.BookRepository;
import org.neoa.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.neoa.repository.specifications.AuthorSpecs.isAgeGt45;

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


    @Transactional
    public void fetchingAllAuthorsWithBooks() {
        log.info("Fetching Author with book using @EntityGraph");
        List<Author> authors = authorRepository.findAll();
        authors.forEach(System.out::println);
    }


    @Transactional
    public void fetchAllAuthorsWithLazyMode() {
        log.info("Fetching Author without books using @EntityGraph");
        List<Author> authors = authorRepository.findAll();
        authors.forEach(System.out::println);
    }

    @Transactional
    public void fetchAllAuthorByAgeLessThan() {
        log.info("Fetching Author using @EntityGraph and Query Builder Mechanism");
        List<Author> authors = authorRepository.findByAgeLessThanOrderByNameDesc(35);
        authors.forEach(System.out::println);
    }

    @Transactional
    public void fetchAllAuthorsByAgeLessThanUsingSpecification() {
        log.info("Fetching Author using @EntityGraph and Specification");
        List<Author> authors = authorRepository.findAll(isAgeGt45());
        authors.forEach(System.out::println);
    }

    @Transactional
    public void fetchAllAuthorsWhichAgeIsBetween20And40() {
        log.info("Fetching Author using @EntityGraph, subGraphs, @Query and JPQL");
        List<Author> authors = authorRepository.fetchAllAgeBetween20And40();
        authors.forEach(System.out::println);
    }

    @Transactional
    public void fetchAllAuthorsWithBooksUsingAttributePaths() {
        log.info("Fetching Author using @EntityGraph and attributePaths");
        List<Author> authors = authorRepository.findAll();
        authors.forEach(System.out::println);
    }

    @Transactional
    public void fetchingAllAuthorsWithBooksAndPublishers() {
        log.info("Fetching Author with book using @EntityGraph");
        List<Author> authors = authorRepository.findAll();
        authors.forEach(System.out::println);
    }

    @Transactional
    public void fetchingAllAuthorsUsingAdHocEntityGraphsAndSubGraphs() {
        log.info("Fetching Author with @EntityGraph, SubGraphs at a Ad Hoc level");
        List<Author> authors = authorRepository.findAll();
        authors.forEach(System.out::println);
    }

}


