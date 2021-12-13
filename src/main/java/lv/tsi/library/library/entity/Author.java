package lv.tsi.library.library.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;

    public Long getId() {
        return id;
    }

    public Author setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Author setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Author setBooks(List<Book> books) {
        this.books = books;
        return this;
    }
}
