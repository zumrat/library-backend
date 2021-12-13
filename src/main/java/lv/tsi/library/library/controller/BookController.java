package lv.tsi.library.library.controller;

import lv.tsi.library.library.dto.BookDto;
import lv.tsi.library.library.service.BookService;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getBooks() {
        return bookService.getBooks(null);
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @GetMapping("/search")
    public List<BookDto> findBooksByString(@RequestParam String query) {
        return bookService.getBooks(query);
    }

    @GetMapping("/new")
    public List<BookDto> getBooksSortedByNew() {
        return bookService.getBooksSorted(Sort.by(Sort.Direction.DESC, "id"));
    }

    @GetMapping("/popular")
    public List<BookDto> getBooksSortedByCheckOutCount() {
        return bookService.getBooksSorted(Sort.by(Sort.Direction.DESC, "checkOuts"));
    }

    @PostMapping
    public BookDto createBook(@RequestBody @Validated BookDto request) {
        return bookService.createBook(request);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
