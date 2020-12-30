package org.neoa.repository;

import org.neoa.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional
    @Query("SELECT b FROM Book b WHERE b.author.id = :id")
    List<Book> fetchBooksOfAuthorById(Long id);
}
