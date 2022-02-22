package lv.tsi.library.library.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class AuthorDto {

    private final Long id;
    private final String fullName;
    private List<BookDto> books;

    AuthorDto(Long id, String fullName, List<BookDto> books) {
        this.id = id;
        this.fullName = fullName;
        this.books = books;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDto authorDto = (AuthorDto) o;
        return Objects.equals(id, authorDto.id) && Objects.equals(fullName, authorDto.fullName) && Objects.equals(books, authorDto.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, books);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AuthorDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("fullName='" + fullName + "'")
                .add("books=" + books)
                .toString();
    }

    public void setBooks(List<BookDto> bookDtos) {
        this.books = bookDtos;
    }

    public static class Builder {
        private Long id;
        private String fullName;
        private List<BookDto> books = new ArrayList<>();

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder books(List<BookDto> books) {
            this.books = books;
            return this;
        }

        public AuthorDto build() {
            return new AuthorDto(id, fullName, books);
        }
    }
}
