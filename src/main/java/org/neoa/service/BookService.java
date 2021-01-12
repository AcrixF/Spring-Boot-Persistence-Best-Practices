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

    @Transactional(readOnly = true)
    public Book fetchBookByAuthor() {
        log.info("Fetching Book By Author");
        Author author = authorRepository.findById(1L).orElseThrow();
        return bookRepository.fetchBookByAuthor(author);
    }
}
