package lv.tsi.library.library.mapper;

import lv.tsi.library.library.commontype.BookStatus;
import lv.tsi.library.library.dto.AuthorDto;
import lv.tsi.library.library.dto.BookDto;
import lv.tsi.library.library.entity.Author;
import lv.tsi.library.library.entity.Book;
import lv.tsi.library.library.entity.BookGenre;
import lv.tsi.library.library.entity.Genre;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookMapperTest {

    public static final String SEND_HELP = "SEND-HELP";
    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private BookMapper bookMapper;

    @Test
    void testToEntity() {

        var result = bookMapper.toEntity(createBookDto());
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(createBook());
    }

    @Test
    void testToDto() {
        var author = new Author();
        var book = createBook()
                .setAuthor(author)
                .setBookGenres(List.of(new BookGenre().setGenre(new Genre().setValue(SEND_HELP))));

        when(authorMapper.toDto(author)).thenReturn(AuthorDto.builder().build());

        var result = bookMapper.toDto(book);

        assertThat(result).isEqualTo(createBookDto());
    }

    private Book createBook() {
        return new Book()
                .setId(1L)
                .setBookStatus(BookStatus.AVAILABLE)
                .setDescription("description")
                .setIsbn("isbn")
                .setTitle("title")
                .setPictureUrl("url")
                .setEdition("edition");
    }

    private BookDto createBookDto() {
        return BookDto.builder()
                .bookStatus(BookStatus.AVAILABLE)
                .id(1L)
                .description("description")
                .isbn("isbn")
                .title("title")
                .pictureUrl("url")
                .edition("edition")
                .genres(List.of(SEND_HELP))
                .authorDto(AuthorDto.builder().build())
                .build();
    }
}