package lv.tsi.library.library.dto;

import lv.tsi.library.library.commontype.BookStatus;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class BookDto {

    private Long id;
    private String isbn;
    private String title;
    private String edition;
    private List<String> genres;
    private String pictureUrl;
    private String description;
    private BookStatus bookStatus;

    private AuthorDto authorDto;

    public Long getId() {
        return id;
    }

    public BookDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookDto setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getEdition() {
        return edition;
    }

    public BookDto setEdition(String edition) {
        this.edition = edition;
        return this;
    }

    public List<String> getGenres() {
        return genres;
    }

    public BookDto setGenres(List<String> genres) {
        this.genres = genres;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public BookDto setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BookDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public AuthorDto getAuthorDto() {
        return authorDto;
    }

    public BookDto setAuthorDto(AuthorDto authorDto) {
        this.authorDto = authorDto;
        return this;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public BookDto setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(id, bookDto.id) && Objects.equals(isbn, bookDto.isbn) && Objects.equals(title, bookDto.title) && Objects.equals(edition, bookDto.edition) && Objects.equals(genres, bookDto.genres) && Objects.equals(pictureUrl, bookDto.pictureUrl) && Objects.equals(description, bookDto.description) && bookStatus == bookDto.bookStatus && Objects.equals(authorDto, bookDto.authorDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, title, edition, genres, pictureUrl, description, bookStatus, authorDto);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BookDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("isbn='" + isbn + "'")
                .add("title='" + title + "'")
                .add("edition='" + edition + "'")
                .add("genres=" + genres)
                .add("pictureUrl='" + pictureUrl + "'")
                .add("description='" + description + "'")
                .add("bookStatus=" + bookStatus)
                .add("authorDto=" + authorDto)
                .toString();
    }
}
