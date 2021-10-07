package com.vladwild;

import com.vladwild.config.JpaH2Runner;
import com.vladwild.resolve.entity.graph.entity.BookGraph;
import com.vladwild.resolve.entity.graph.repository.BooksRepositoryGraph;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.jdbc.Sql;

/**
 * Решение проблемы N + 1 с помощью графа для конкретных филдов
 * Смотреть BooksRepositoryGraph.class
 */
@EntityScan(basePackages = "com.vladwild.resolve.entity.graph.entity")
@EnableJpaRepositories(basePackages = "com.vladwild.resolve.entity.graph.repository")
public class EntityGraphTest extends JpaH2Runner {

    @Autowired
    private BooksRepositoryGraph booksRepositoryGraph;

    @Test
    @Sql("classpath:insert.sql")
    public void problemTest() {
        Iterable<BookGraph> bookProblems = booksRepositoryGraph.findAll();
        System.out.println(bookProblems);
    }

    /**
     * Логи хибернейта:
     *
     * Hibernate:
     *     select
     *         bookgraph0_.id as id1_1_0_,
     *         authorgrap1_.id as id1_0_1_,
     *         bookgraph0_.author_id as author_i3_1_0_,
     *         bookgraph0_.name as name2_1_0_,
     *         authorgrap1_.name as name2_0_1_
     *     from
     *         book bookgraph0_
     *     left outer join
     *         author authorgrap1_
     *             on bookgraph0_.author_id=authorgrap1_.id
     * Hibernate:
     *     select
     *         bookgraph0_.id as id1_1_0_,
     *         authorgrap1_.id as id1_0_1_,
     *         bookgraph0_.author_id as author_i3_1_0_,
     *         bookgraph0_.name as name2_1_0_,
     *         authorgrap1_.name as name2_0_1_
     *     from
     *         book bookgraph0_
     *     left outer join
     *         author authorgrap1_
     *             on bookgraph0_.author_id=authorgrap1_.id
     *
     */
}
