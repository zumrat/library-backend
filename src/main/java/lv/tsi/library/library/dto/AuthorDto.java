package lv.tsi.library.library.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class AuthorDto {

    private Long id;
    private String fullName;

    private List<BookDto> books = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public AuthorDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public AuthorDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public AuthorDto setBooks(List<BookDto> books) {
        this.books = books;
        return this;
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
}
