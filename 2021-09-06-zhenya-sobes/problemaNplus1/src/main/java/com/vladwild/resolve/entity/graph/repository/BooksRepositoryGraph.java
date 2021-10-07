package com.vladwild.resolve.entity.graph.repository;

import com.vladwild.resolve.entity.graph.entity.BookGraph;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepositoryGraph extends CrudRepository<BookGraph, Long> {

    /**
     * Граф для конкретных филдов
     */
    @EntityGraph(attributePaths = "author")
    Iterable<BookGraph> findAll();
}