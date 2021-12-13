package lv.tsi.library.library.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
    private List<BookGenre> bookGenres;

    public Long getId() {
        return id;
    }

    public Genre setId(Long id) {
        this.id = id;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Genre setValue(String value) {
        this.value = value;
        return this;
    }

    public List<BookGenre> getBookGenres() {
        return bookGenres;
    }

    public Genre setBookGenres(List<BookGenre> bookGenres) {
        this.bookGenres = bookGenres;
        return this;
    }
}
