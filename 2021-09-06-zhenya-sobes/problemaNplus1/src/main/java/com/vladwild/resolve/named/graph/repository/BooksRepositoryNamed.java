package com.vladwild.resolve.named.graph.repository;

import com.vladwild.resolve.named.graph.entity.BookNamed;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepositoryNamed extends CrudRepository<BookNamed, Long> {

    /**
     * Именной граф
     */
    @EntityGraph("BookNamed.author")
    Iterable<BookNamed> findAll();
}