package com.vladwild.problem.entity;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class BookProblem {

    private Long id;
    private String name;
    private AuthorProblem author;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "author_id")
    public AuthorProblem getAuthor() {
        return author;
    }

    public void setAuthor(AuthorProblem author) {
        this.author = author;
    }
}