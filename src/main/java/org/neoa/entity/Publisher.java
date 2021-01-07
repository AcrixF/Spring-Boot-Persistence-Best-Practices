package org.neoa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Accessors(chain = true)
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String company;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "publisher",
            orphanRemoval = true
    )
    private List<Book> books = new ArrayList<>();

    @Override
    public String toString() {
        return "Publisher{ " + "company = " + company + "book = " + books + "}";
    }
}
