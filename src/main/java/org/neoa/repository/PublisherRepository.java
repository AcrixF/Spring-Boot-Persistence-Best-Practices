package org.neoa.repository;

import org.neoa.entity.Publisher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    @Override
    @EntityGraph(
            attributePaths = {"books.author"},
            type = EntityGraph.EntityGraphType.FETCH
    )
    List<Publisher> findAll();

    @EntityGraph(
            attributePaths = {"books.author"},
            type = EntityGraph.EntityGraphType.FETCH
    )
    @Query("select p from Publisher  p where p.id > 1 and p.id < 3")
    List<Publisher> fetchAllIdBetween1And3();
}
