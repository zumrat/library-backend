package lv.tsi.library.library.mapper;

import lv.tsi.library.library.dto.BookDto;
import lv.tsi.library.library.entity.Book;
import lv.tsi.library.library.entity.BookGenre;
import lv.tsi.library.library.entity.Genre;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookMapper {

    private final AuthorMapper authorMapper;

    public BookMapper(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    public Book toEntity(BookDto dto) {
        return new Book()
                .setBookStatus(dto.getBookStatus())
                .setId(dto.getId())
                .setDescription(dto.getDescription())
                .setIsbn(dto.getIsbn())
                .setTitle(dto.getTitle())
                .setPictureUrl(dto.getPictureUrl())
                .setEdition(dto.getEdition());
    }

    public BookDto toDto(Book entity) {
        return BookDto.builder()
                .bookStatus(entity.getBookStatus())
                .genres(entity.getBookGenres().stream()
                        .map(BookGenre::getGenre)
                        .map(Genre::getValue)
                        .collect(Collectors.toList()))
                .authorDto(authorMapper.toDto(entity.getAuthor()))
                .id(entity.getId())
                .description(entity.getDescription())
                .isbn(entity.getIsbn())
                .title(entity.getTitle())
                .pictureUrl(entity.getPictureUrl())
                .edition(entity.getEdition())
                .build();
    }

}
