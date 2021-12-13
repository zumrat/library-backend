package lv.tsi.library.library.entity;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CheckOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate reserveDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reader_id", nullable = false)
    private Reader reader;

    public Long getId() {
        return id;
    }

    public CheckOut setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getReserveDate() {
        return reserveDate;
    }

    public CheckOut setReserveDate(LocalDate reserveDate) {
        this.reserveDate = reserveDate;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public CheckOut setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public CheckOut setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public Book getBook() {
        return book;
    }

    public CheckOut setBook(Book book) {
        this.book = book;
        return this;
    }

    public Reader getReader() {
        return reader;
    }

    public CheckOut setReader(Reader reader) {
        this.reader = reader;
        return this;
    }
}
