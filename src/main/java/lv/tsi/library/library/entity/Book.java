package lv.tsi.library.library.entity;

import lv.tsi.library.library.commontype.BookStatus;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String title;
    private String edition;
    private String pictureUrl;
    private String description;
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<CheckOut> checkOuts;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookGenre> bookGenres;

    public Long getId() {
        return id;
    }

    public Book setId(Long id) {
        this.id = id;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public Book setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getEdition() {
        return edition;
    }

    public Book setEdition(String edition) {
        this.edition = edition;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public Book setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Book setDescription(String description) {
        this.description = description;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public Book setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public List<CheckOut> getCheckOuts() {
        return checkOuts;
    }

    public Book setCheckOuts(List<CheckOut> checkOuts) {
        this.checkOuts = checkOuts;
        return this;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public Book setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
        return this;
    }

    public List<BookGenre> getBookGenres() {
        return bookGenres;
    }

    public Book setBookGenres(List<BookGenre> bookGenres) {
        this.bookGenres = bookGenres;
        return this;
    }
}
