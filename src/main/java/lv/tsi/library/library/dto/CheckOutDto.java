package lv.tsi.library.library.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public class CheckOutDto {

    private final Long id;
    private final LocalDate reserveDate;
    @NotNull
    private final LocalDate dueDate;
    private final LocalDate returnDate;
    @NotNull
    private ReaderDto reader;
    @NotNull
    private final BookDto book;

    private CheckOutDto(Long id, LocalDate reserveDate, @NotNull LocalDate dueDate, LocalDate returnDate, @NotNull ReaderDto reader, @NotNull BookDto book) {
        this.id = id;
        this.reserveDate = reserveDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.reader = reader;
        this.book = book;
    }

    public static Builder builder() {
        return new Builder();
    }


    public Long getId() {
        return id;
    }



    public LocalDate getReserveDate() {
        return reserveDate;
    }



    public LocalDate getDueDate() {
        return dueDate;
    }



    public LocalDate getReturnDate() {
        return returnDate;
    }



    public ReaderDto getReader() {
        return reader;
    }



    public BookDto getBook() {
        return book;
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

    public CheckOutDto setReader(ReaderDto readerDto) {
        this.reader = readerDto;
        return this;
    }

    public static class Builder {
        private Long id;
        private LocalDate reserveDate;
        private LocalDate dueDate;
        private LocalDate returnDate;
        private ReaderDto reader;
        private BookDto book;

        Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder reserveDate(LocalDate reserveDate) {
            this.reserveDate = reserveDate;
            return this;
        }

        public Builder dueDate(@NotNull LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder returnDate(LocalDate returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public Builder reader(@NotNull ReaderDto reader) {
            this.reader = reader;
            return this;
        }

        public Builder book(@NotNull BookDto book) {
            this.book = book;
            return this;
        }

        public CheckOutDto build() {
            return new CheckOutDto(id, reserveDate, dueDate, returnDate, reader, book);
        }


    }
}
