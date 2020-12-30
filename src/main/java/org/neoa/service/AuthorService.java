package org.neoa.service;

import lombok.extern.log4j.Log4j2;
import org.neoa.entity.Author;
import org.neoa.entity.Book;
import org.neoa.repository.AuthorRepository;
import org.neoa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public void insertAuthor() {
        log.info("Creating Author");
        Author author = new Author()
                .setName("Joana Nimar")
                .setAge(34)
                .setGenre("History");

        authorRepository.saveAndFlush(author);
    }

    @Transactional
    public void addNewBookToAuthor() {
        log.info("Inserting new Book");
        Author author = authorRepository.getOne(1L);

        Book bookOne = new Book()
                .setIsbn("003-JN")
                .setTitle("History Details")
                .setAuthor(author);

        Book bookTwo = new Book()
                .setIsbn("002-JN")
                .setTitle("History Of The World")
                .setAuthor(author);

        Book bookThree = new Book()
                .setIsbn("001-JN")
                .setTitle("History Of The World II")
                .setAuthor(author);

        bookRepository.saveAndFlush(bookOne);
        bookRepository.saveAndFlush(bookTwo);
        bookRepository.saveAndFlush(bookThree);

        //Hibernate Dirty Mechanisms
        bookOne.setIsbn("not available");
    }

    @Transactional
    public void fetchBooksOfAuthorById() {
        log.info("Fetching books by Author's Id");
        List<Book> books = bookRepository.fetchBooksOfAuthorById(1L);

        //Hibernate Dirty Mechanisms
        books.get(0).setIsbn("Now Available");
    }

    public void fetchPageBooksOfAuthorById() {
        log.info("Fetching Page books by Author's Id ");
        Page<Book> books = bookRepository.fetchPageBooksOfAuthorById(1L,
                PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "title")));
        books.forEach(System.out::println);
    }

    @Transactional
    public void fetchBooksOfAuthorByIdAndAddNewBook() {
        log.info("Fetching Author's books and saving a new book");
        List<Book> books = bookRepository.fetchBooksOfAuthorById(1L);

        Book bookFour = new Book()
                .setIsbn("001-JN")
                .setTitle("History Of The World II")
                .setAuthor(books.get(0).getAuthor());

        books.add(bookRepository.save(bookFour));
    }

    @Transactional
    public void fetchBooksOfAuthorByIdAndDeleteFirstBook() {
        log.info("Deleting first book from collection");
        List<Book> books = bookRepository.fetchBooksOfAuthorById(1L);
        bookRepository.delete(books.remove(0));
    }
}
