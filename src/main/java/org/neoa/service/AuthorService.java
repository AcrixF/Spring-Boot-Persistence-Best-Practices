package org.neoa.service;

import lombok.extern.log4j.Log4j2;
import org.neoa.entity.Author;
import org.neoa.entity.Book;
import org.neoa.repository.AuthorRepository;
import org.neoa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // ------------------------------ Item 7 ---------------------------------------
    @Transactional
    public void fetchAuthorById() {
        Author author = authorRepository.findById(1L).orElseThrow();
        List<Book> books = author.getBooks();
        books.forEach(System.out::println);
    }

}


