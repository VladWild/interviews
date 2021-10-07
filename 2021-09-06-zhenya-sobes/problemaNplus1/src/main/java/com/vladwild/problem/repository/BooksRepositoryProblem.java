package com.vladwild.problem.repository;

import com.vladwild.problem.entity.BookProblem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepositoryProblem extends CrudRepository<BookProblem, Long> {

}