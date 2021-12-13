package lv.tsi.library.library.service;

import lv.tsi.library.library.commontype.BookStatus;
import lv.tsi.library.library.dto.AuthorDto;
import lv.tsi.library.library.dto.BookDto;
import lv.tsi.library.library.entity.Book;
import lv.tsi.library.library.entity.BookGenre;
import lv.tsi.library.library.exception.LibraryBadRequestException;
import lv.tsi.library.library.exception.LibraryEntityNotFoundException;
import lv.tsi.library.library.mapper.AuthorMapper;
import lv.tsi.library.library.mapper.BookMapper;
import lv.tsi.library.library.repository.AuthorRepository;
import lv.tsi.library.library.repository.BookRepository;
import lv.tsi.library.library.repository.GenreRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;
    private final GenreRepository genreRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, BookMapper bookMapper, AuthorMapper authorMapper, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookMapper = bookMapper;
        this.authorMapper = authorMapper;
        this.genreRepository = genreRepository;
    }

    public List<BookDto> getBooks(String query) {
        var books = Optional.ofNullable(query)
                .map(it -> bookRepository.findAllDistinctByAuthorFullNameContainingIgnoreCaseOrTitleContainingIgnoreCaseOrBookGenresGenreValueContainingIgnoreCase(it, it, it))
                .orElseGet(bookRepository::findAll);
        return books.stream()
                .map(this::toBookDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> getBooksSorted(Sort sort) {
        var books = bookRepository.findAll(sort);
        return books.stream()
                .map(this::toBookDto)
                .collect(Collectors.toList());
    }

    public BookDto findById(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(LibraryEntityNotFoundException.withMessage(String.format("Book with id=%s not found", id)));
        var bookDto = bookMapper.toDto(book);

        var author = book.getAuthor();
        var books = author.getBooks();
        var authorDto = authorMapper.toDto(author);
        var bookDtos = books.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
        authorDto.setBooks(bookDtos);

        return bookDto.setAuthorDto(authorDto);
    }

    public BookDto createBook(BookDto request) {
        var book = bookMapper.toEntity(request);
        book.setBookStatus(BookStatus.AVAILABLE);
        var author = getAuthorDto(request)
                .map(AuthorDto::getFullName)
                .flatMap(authorRepository::findByFullName)
                .or(() -> getAuthorDto(request).map(authorMapper::toEntity))
                .orElseThrow(LibraryBadRequestException.withMessage("Author can not be null"));
        book.setAuthor(author);

        var genres = genreRepository.findAllByValueIn(request.getGenres());
        var bookGenres = genres.stream()
                .map(it -> new BookGenre().setBook(book).setGenre(it))
                .collect(Collectors.toList());
        book.setBookGenres(bookGenres);

        var saved = bookRepository.save(book);

        return bookMapper.toDto(saved);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    private Optional<AuthorDto> getAuthorDto(BookDto request) {
        return Optional.ofNullable(request)
                .map(BookDto::getAuthorDto);
    }

    private BookDto toBookDto(Book book) {
        var dto = bookMapper.toDto(book);
        var author = authorMapper.toDto(book.getAuthor());
        return dto.setAuthorDto(author);
    }
}
