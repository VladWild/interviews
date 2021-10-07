package com.vladwild;

import com.vladwild.config.JpaH2Runner;
import com.vladwild.problem.entity.BookProblem;
import com.vladwild.problem.repository.BooksRepositoryProblem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.jdbc.Sql;

/**
 * Тест в котором есть проблема N + 1
 */
@EntityScan(basePackages = "com.vladwild.problem.entity")
@EnableJpaRepositories(basePackages = "com.vladwild.problem.repository")
public class ProblemTest extends JpaH2Runner {

    @Autowired
    private BooksRepositoryProblem booksRepository;

    @Test
    @Sql("classpath:insert.sql")
    public void problemTest() {
        Iterable<BookProblem> bookProblems = booksRepository.findAll();
        System.out.println(bookProblems);
    }

    /**
     * Логи хибернейта:
     *
     * Hibernate:
     *     select
     *         bookproble0_.id as id1_1_,
     *         bookproble0_.author_id as author_i3_1_,
     *         bookproble0_.name as name2_1_
     *     from
     *         book bookproble0_
     * Hibernate:
     *     select
     *         bookproble0_.id as id1_1_,
     *         bookproble0_.author_id as author_i3_1_,
     *         bookproble0_.name as name2_1_
     *     from
     *         book bookproble0_
     * Hibernate:
     *     select
     *         authorprob0_.id as id1_0_0_,
     *         authorprob0_.name as name2_0_0_
     *     from
     *         author authorprob0_
     *     where
     *         authorprob0_.id=?
     * Hibernate:
     *     select
     *         authorprob0_.id as id1_0_0_,
     *         authorprob0_.name as name2_0_0_
     *     from
     *         author authorprob0_
     *     where
     *         authorprob0_.id=?
     * 2021-10-07 14:14:58.313 TRACE 27880 --- [           main] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [BIGINT] - [1]
     * 2021-10-07 14:14:58.321 DEBUG 27880 --- [           main] org.hibernate.SQL                        :
     * Hibernate:
     *     select
     *         authorprob0_.id as id1_0_0_,
     *         authorprob0_.name as name2_0_0_
     *     from
     *         author authorprob0_
     *     where
     *         authorprob0_.id=?
     * Hibernate:
     *     select
     *         authorprob0_.id as id1_0_0_,
     *         authorprob0_.name as name2_0_0_
     *     from
     *         author authorprob0_
     *     where
     *         authorprob0_.id=?
     *
     */
}
