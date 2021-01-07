package org.neoa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
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
            mappedBy = "author",
            orphanRemoval = true
    )
    private List<Book> books = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "author",
            orphanRemoval = true
    )
    @Where(clause = "price <= 20")
    private List<Book> cheapBooks = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "author",
            orphanRemoval = true
    )
    @Where(clause = "price > 20")
    private List<Book> restOfBooks = new ArrayList<>();

    public void addBook(Book book) {
        this.books.add(book);
        book.setAuthor(this);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
        book.setAuthor(this);
    }

    public void removeBooks() {
        Iterator<Book> iterator = this.books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            book.setAuthor(this);
            iterator.remove();
        }
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
                + ", genre= " + genre + ", age= " + age + "books= " + books + '}';
    }
}
