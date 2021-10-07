package com.vladwild.resolve.entity.graph.entity;

import com.vladwild.problem.entity.AuthorProblem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class BookGraph {

    private Long id;
    private String name;
    private AuthorGraph author;

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
    public AuthorGraph getAuthor() {
        return author;
    }

    public void setAuthor(AuthorGraph author) {
        this.author = author;
    }
}