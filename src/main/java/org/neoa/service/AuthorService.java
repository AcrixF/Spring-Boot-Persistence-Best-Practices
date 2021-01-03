package org.neoa.service;

import lombok.extern.log4j.Log4j2;
import org.neoa.entity.Author;
import org.neoa.entity.Book;
import org.neoa.repository.AuthorRepository;
import org.neoa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
        log.info("Inserting authors");

        Author alicia = new Author()
                .setAge(34)
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

        Author martin = new Author()
                .setAge(34)
                .setGenre("Technology")
                .setName("Martin");

        Book refactoring = new Book()
                .setTitle("Refactoring")
                .setIsbn("001-RFIS-01");

        martin.addBook(refactoring);

        authorRepository.saveAll(List.of(alicia, martin));
    }

    @Transactional
    public void deleteViaIdentifiers() {
        log.info("Deleting books by Identifier");
        Author author = authorRepository.findById(1L).orElseThrow();
        bookRepository.deleteByAuthorIdentifier(author.getId());
        authorRepository.deleteByAuthorIdentifier(author.getId());
    }

    @Transactional
    public void deleteViaBulkIn() {
        log.info("Deleting books and Authors");
        List<Author> authors = authorRepository.findByAge(34);
        bookRepository.deleteBulkByAuthors(authors);
        authorRepository.deleteInBatch(authors);
    }

    @Transactional
    public void deleteViaDeleteInBatch() {
        log.info("Deleting authors and books that already exist in the context");
        Author author = authorRepository.getOne(1L);
        bookRepository.deleteInBatch(author.getBooks());
        authorRepository.deleteInBatch(List.of(author));
    }

    @Transactional
    public void deleteViaHardCodedIdentifiers() {
        log.info("Deleting authors and books by it's Identifier");
        bookRepository.deleteByAuthorIdentifier(1L);
        authorRepository.deleteByAuthorIdentifier(1L);
    }

    @Transactional
    public void deleteViaBulkHardCodedIdentifiers() {
        log.info("Deleting Authors in Bulk using it's Identifiers");
        List<Long> authorsIds = Arrays.asList(1L, 2L);
        bookRepository.deleteBulkByAuthorIdentifier(authorsIds);
        authorRepository.deleteBulkByIdentifier(authorsIds);
    }

}


