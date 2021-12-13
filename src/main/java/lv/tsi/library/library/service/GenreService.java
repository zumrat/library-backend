package lv.tsi.library.library.service;

import lv.tsi.library.library.entity.BookGenre;
import lv.tsi.library.library.entity.Genre;
import lv.tsi.library.library.exception.LibraryEntityNotFoundException;
import lv.tsi.library.library.repository.BookGenreRepository;
import lv.tsi.library.library.repository.BookRepository;
import lv.tsi.library.library.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final BookGenreRepository bookGenreRepository;
    private final BookRepository bookRepository;

    public GenreService(GenreRepository genreRepository, BookGenreRepository bookGenreRepository, BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.bookGenreRepository = bookGenreRepository;
        this.bookRepository = bookRepository;
    }

    public List<String> getAllGenres() {
        return StreamSupport.stream(genreRepository.findAll().spliterator(), false)
                .map(Genre::getValue)
                .collect(Collectors.toList());
    }

    public List<String> updateGenresForBook(Long bookId, List<String> genres) {
        bookGenreRepository.deleteAllByBookIdAndGenreValueNotIn(bookId, genres);
        var book = bookRepository.findById(bookId)
                .orElseThrow(LibraryEntityNotFoundException.withMessage(String.format("Book with id=%s does not exist", bookId)));
        var existingLinks = book.getBookGenres();
        var existingStrings = existingLinks.stream()
                .map(BookGenre::getGenre)
                .map(Genre::getValue)
                .collect(Collectors.toList());
        var existingGenresMap = genreRepository.findAllByValueIn(genres).stream()
                .collect(Collectors.toMap(Genre::getValue, it -> it));

        List<BookGenre> result = new ArrayList<>();
        for (var genreString : genres) {
            if (existingStrings.contains(genreString)) {
                continue;
            }
            var genre = Optional.of(genreString)
                    .map(existingGenresMap::get)
                    .orElseGet(() -> new Genre().setValue(genreString));
            var bookGenre = new BookGenre().setGenre(genre).setBook(book);
            result.add(bookGenre);
        }
        var saved = bookGenreRepository.saveAll(result);
        return StreamSupport.stream(saved.spliterator(), false)
                .map(BookGenre::getGenre)
                .map(Genre::getValue)
                .collect(Collectors.toList());
    }
}
