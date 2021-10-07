package com.vladwild.resolve.named.graph.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

@Entity
@Table(name = "book")
@NamedEntityGraph(
        name = "BookNamed.author",
        attributeNodes = @NamedAttributeNode("author")
)
public class BookNamed {

    private Long id;
    private String name;
    private AuthorNamed author;

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
    public AuthorNamed getAuthor() {
        return author;
    }

    public void setAuthor(AuthorNamed author) {
        this.author = author;
    }
}
