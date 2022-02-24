package lv.tsi.library.library.service;

import lv.tsi.library.library.commontype.BookStatus;
import lv.tsi.library.library.dto.AuthorDto;
import lv.tsi.library.library.dto.BookDto;
import lv.tsi.library.library.entity.Author;
import lv.tsi.library.library.entity.Book;
import lv.tsi.library.library.entity.Genre;
import lv.tsi.library.library.mapper.AuthorMapper;
import lv.tsi.library.library.mapper.BookMapper;
import lv.tsi.library.library.repository.AuthorRepository;
import lv.tsi.library.library.repository.BookRepository;
import lv.tsi.library.library.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    private static final String FULL_NAME = "author";
    private static final String GENRE = "genre";
    @Mock
    private BookMapper bookMapper;

    @Mock
    private AuthorMapper authorMapper;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void testCreateBook() {
        var request = BookDto.builder()
                .authorDto(AuthorDto.builder()
                        .fullName(FULL_NAME)
                        .build())
                .genres(List.of(GENRE))
                .build();
        var entity = new Book();
        var saved = entity.setId(1L);

        when(bookMapper.toEntity(request)).thenReturn(entity);
        when(authorRepository.findByFullName(FULL_NAME))
                .thenReturn(Optional.of(new Author().setFullName(FULL_NAME)));
        when(genreRepository.findAllByValueIn(List.of(GENRE)))
                .thenReturn(List.of(new Genre().setValue(GENRE)));
        when(bookRepository.save(entity))
                .thenReturn(saved);
        BookDto bookDto = BookDto.builder().description("123").build();
        when(bookMapper.toDto(saved))
                .thenReturn(bookDto);

        var result = bookService.createBook(request);

        assertThat(result).isNotNull().isEqualTo(bookDto);
        assertThat(entity.getBookStatus()).isEqualTo(BookStatus.AVAILABLE);
        assertThat(entity.getAuthor()).isNotNull().extracting(Author::getFullName).isEqualTo(FULL_NAME);
        assertThat(entity.getBookGenres()).isNotNull().isNotEmpty().extracting(it -> it.getGenre().getValue()).contains(GENRE);

    }

    @Test
    void testDeleteBook() {
        var id = 1L;

        bookService.deleteBook(id);

        Mockito.verify(bookRepository).deleteById(id);
    }

    @Test
    void testGetBooks() {
        var query = "Author name";
        var book = new Book()
                .setAuthor(new Author()
                        .setFullName(query));
        when(bookRepository.findAllDistinctByAuthorFullNameContainingIgnoreCaseOrTitleContainingIgnoreCaseOrBookGenresGenreValueContainingIgnoreCase(query, query, query))
                .thenReturn(List.of(book));
        when(bookMapper.toDto(book)).thenReturn(BookDto.builder().build());
        when(authorMapper.toDto(book.getAuthor())).thenReturn(AuthorDto.builder().build());

        var result = bookService.getBooks(query);

        Mockito.verify(bookMapper).toDto(book);
        Mockito.verify(authorMapper).toDto(book.getAuthor());
        assertThat(result).isNotNull();
    }

    @Test
    void testGetBooksSorted(){
        Book book = new Book();
        var sort = Sort.by(Sort.Direction.DESC, "id");
        when(bookRepository.findAll(sort)).thenReturn(List.of(book));
        when(bookMapper.toDto(book)).thenReturn(BookDto.builder().build());
        when(authorMapper.toDto(book.getAuthor())).thenReturn(AuthorDto.builder().build());

        var result = bookService.getBooksSorted(sort);

        Mockito.verify(bookRepository).findAll(sort);
        Mockito.verify(bookMapper).toDto(book);
        Mockito.verify(authorMapper).toDto(book.getAuthor());
        assertThat(result).isNotNull();
    }

    @Test
    void testFindById() {
        var id = 1L;
        var author = new Author();
        var book = new Book().setId(id).setAuthor(author);
        author.setBooks(List.of(book));
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        when(bookMapper.toDto(book)).thenReturn(BookDto.builder().id(id).build());
        when(authorMapper.toDto(author)).thenReturn(AuthorDto.builder().build());

        var result = bookService.findById(id);

        Mockito.verify(bookMapper, times(2)).toDto(book);
        Mockito.verify(authorMapper).toDto(book.getAuthor());
        assertThat(result).isNotNull();

    }
}