package org.neoa.repository;

import org.neoa.entity.Author;
import org.neoa.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b where b.author = ?1")
    Book fetchBookByAuthor(Author author);
}
