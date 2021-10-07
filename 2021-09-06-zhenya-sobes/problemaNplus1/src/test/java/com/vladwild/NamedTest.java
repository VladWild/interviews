package com.vladwild;

import com.vladwild.config.JpaH2Runner;
import com.vladwild.resolve.entity.graph.entity.BookGraph;
import com.vladwild.resolve.named.graph.entity.BookNamed;
import com.vladwild.resolve.named.graph.repository.BooksRepositoryNamed;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.jdbc.Sql;

/**
 * Решение проблемы N + 1 с помощью именного графа
 * Смотреть BooksRepositoryNamed.class, BookNamed.class
 */
@EntityScan(basePackages = "com.vladwild.resolve.named.graph.entity")
@EnableJpaRepositories(basePackages = "com.vladwild.resolve.named.graph.repository")
public class NamedTest extends JpaH2Runner {

    @Autowired
    private BooksRepositoryNamed booksRepositoryNamed;

    @Test
    @Sql("classpath:insert.sql")
    public void problemTest() {
        Iterable<BookNamed> bookProblems = booksRepositoryNamed.findAll();
        System.out.println(bookProblems);

        /**
         * Hibernate:
         *     select
         *         booknamed0_.id as id1_1_0_,
         *         authorname1_.id as id1_0_1_,
         *         booknamed0_.author_id as author_i3_1_0_,
         *         booknamed0_.name as name2_1_0_,
         *         authorname1_.name as name2_0_1_
         *     from
         *         book booknamed0_
         *     left outer join
         *         author authorname1_
         *             on booknamed0_.author_id=authorname1_.id
         * Hibernate:
         *     select
         *         booknamed0_.id as id1_1_0_,
         *         authorname1_.id as id1_0_1_,
         *         booknamed0_.author_id as author_i3_1_0_,
         *         booknamed0_.name as name2_1_0_,
         *         authorname1_.name as name2_0_1_
         *     from
         *         book booknamed0_
         *     left outer join
         *         author authorname1_
         *             on booknamed0_.author_id=authorname1_.id
         */
    }
}
