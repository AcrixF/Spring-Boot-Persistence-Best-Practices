package org.neoa.service;

import lombok.extern.log4j.Log4j2;
import org.neoa.entity.Author;
import org.neoa.entity.Book;
import org.neoa.repository.AuthorRepository;
import org.neoa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public void addBookToAuthorUsingFindBy() {
        log.info("Adding book to Author using findById");
        Author author = authorRepository.findById(1L).orElseThrow();

        Book book = new Book();
        book.setIsbn("001-MJ");
        book.setTitle("The Canterbury Anthology");
        book.setAuthor(author);

        bookRepository.save(book);
    }

    @Transactional
    public void addBookToAuthorUsingGetOne() {
        log.info("Adding book to Author using getOne");
        Author proxy = authorRepository.getOne(1L);

        Book book = new Book();
        book.setIsbn("001-MJ");
        book.setTitle("The Canterbury Anthology");
        book.setAuthor(proxy);

        bookRepository.save(book);
    }


}
