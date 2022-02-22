package lv.tsi.library.library.dto;

import lv.tsi.library.library.commontype.BookStatus;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class BookDto {

    private final Long id;
    private final String isbn;
    private final String title;
    private final String edition;
    private final List<String> genres;
    private final String pictureUrl;
    private final String description;
    private final BookStatus bookStatus;

    private AuthorDto authorDto;

    private BookDto(Long id, String isbn, String title, String edition, List<String> genres, String pictureUrl, String description, BookStatus bookStatus, AuthorDto authorDto) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.edition = edition;
        this.genres = genres;
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.bookStatus = bookStatus;
        this.authorDto = authorDto;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }



    public String getIsbn() {
        return isbn;
    }



    public String getTitle() {
        return title;
    }



    public String getEdition() {
        return edition;
    }



    public List<String> getGenres() {
        return genres;
    }



    public String getPictureUrl() {
        return pictureUrl;
    }



    public String getDescription() {
        return description;
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

    public static class Builder {
        private Long id;
        private String isbn;
        private String title;
        private String edition;
        private List<String> genres;
        private String pictureUrl;
        private String description;
        private BookStatus bookStatus;
        private AuthorDto authorDto;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder edition(String edition) {
            this.edition = edition;
            return this;
        }

        public Builder genres(List<String> genres) {
            this.genres = genres;
            return this;
        }

        public Builder pictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder bookStatus(BookStatus bookStatus) {
            this.bookStatus = bookStatus;
            return this;
        }

        public Builder authorDto(AuthorDto authorDto) {
            this.authorDto = authorDto;
            return this;
        }

        public BookDto build() {
            return new BookDto(id, isbn, title, edition, genres, pictureUrl, description, bookStatus, authorDto);
        }

    }
}
