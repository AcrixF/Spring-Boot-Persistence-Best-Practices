package org.neoa.repository;

import org.neoa.entity.Publisher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    @Override
    @EntityGraph(
            attributePaths = {"books.author"},
            type = EntityGraph.EntityGraphType.FETCH
    )
    List<Publisher> findAll();
}
