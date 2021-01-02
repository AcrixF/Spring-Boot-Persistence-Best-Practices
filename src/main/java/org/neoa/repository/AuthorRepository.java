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
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = false)
    @Query("delete from Author a where a.id = ?1")
    int deleteByAuthorIdentifier(Long id);

    List<Author> findByAge(int age);
}
