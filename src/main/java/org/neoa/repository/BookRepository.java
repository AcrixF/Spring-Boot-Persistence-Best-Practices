package org.neoa.repository;

import org.neoa.entity.Author;
import org.neoa.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("delete from Book b where b.author.id = ?1")
    int deleteByAuthorIdentifier(Long id);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("delete from Book b where b.author in ?1")
    int deleteBulkByAuthors(List<Author> authors);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("delete from Book b where b.author.id in ?1")
    int deleteBulkByAuthorIdentifier(List<Long> id);

}
