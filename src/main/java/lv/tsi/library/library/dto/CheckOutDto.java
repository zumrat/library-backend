package lv.tsi.library.library.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public class CheckOutDto {

    private Long id;
    private LocalDate reserveDate;
    @NotNull
    private LocalDate dueDate;
    private LocalDate returnDate;
    @NotNull
    private ReaderDto reader;
    @NotNull
    private BookDto book;


    public Long getId() {
        return id;
    }

    public CheckOutDto setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getReserveDate() {
        return reserveDate;
    }

    public CheckOutDto setReserveDate(LocalDate reserveDate) {
        this.reserveDate = reserveDate;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public CheckOutDto setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public CheckOutDto setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public ReaderDto getReader() {
        return reader;
    }

    public CheckOutDto setReader(ReaderDto reader) {
        this.reader = reader;
        return this;
    }

    public BookDto getBook() {
        return book;
    }

    public CheckOutDto setBook(BookDto book) {
        this.book = book;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckOutDto that = (CheckOutDto) o;
        return Objects.equals(id, that.id) && Objects.equals(book, that.book) && Objects.equals(reserveDate, that.reserveDate) && Objects.equals(dueDate, that.dueDate) && Objects.equals(returnDate, that.returnDate) && Objects.equals(reader, that.reader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, reserveDate, dueDate, returnDate, reader);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CheckOutDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("book=" + book)
                .add("reserveDate=" + reserveDate)
                .add("dueDate=" + dueDate)
                .add("returnDate=" + returnDate)
                .add("reader=" + reader)
                .toString();
    }
}
