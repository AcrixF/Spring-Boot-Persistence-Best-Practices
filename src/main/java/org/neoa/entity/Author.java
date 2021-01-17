package org.neoa.entity;

import lombok.Getter;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Entity
@Accessors(chain = true)
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genre;
    private int age;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();


    public Author addBook(Book book) {
        book.setAuthor(this);
        this.books.add(book);
        return this;
    }

    public Author removeBook(Book book) {
        book.setAuthor(null);
        this.books.remove(book);
        return this;
    }


    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<String> getGenre() {
        return Optional.ofNullable(genre);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        return id != null && id.equals(((Author) obj).getId());
    }

    @Override
    public int hashCode() {
        return 2021;
    }

    @Override
    public String toString() {
        return "Author {" + "id= " + id + ", name= " + name
                + ", genre= " + genre + ", age= " + age +'}';
    }
}
