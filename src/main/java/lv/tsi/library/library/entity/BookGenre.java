package lv.tsi.library.library.entity;

import javax.persistence.*;

@Entity
public class BookGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Long getId() {
        return id;
    }

    public BookGenre setId(Long id) {
        this.id = id;
        return this;
    }

    public Book getBook() {
        return book;
    }

    public BookGenre setBook(Book book) {
        this.book = book;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public BookGenre setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }
}
