package lv.tsi.library.library.controller;

import lv.tsi.library.library.LibraryApplication;
import lv.tsi.library.library.commontype.BookStatus;
import lv.tsi.library.library.dto.AuthorDto;
import lv.tsi.library.library.dto.BookDto;
import lv.tsi.library.library.entity.Author;
import lv.tsi.library.library.entity.Book;
import lv.tsi.library.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testGetById() throws Exception {
        var book = new Book()
                .setIsbn("isbn")
                .setTitle("title")
                .setBookStatus(BookStatus.AVAILABLE)
                .setAuthor(new Author().setFullName("author"))                ;
        var saved = bookRepository.save(book);
        var expected = BookDto.builder()
                .isbn("isbn")
                .title("title")
                .genres(List.of())
                .bookStatus(BookStatus.AVAILABLE)
                .id(saved.getId())
                .build();

        var result = webTestClient.get()
                .uri("/api/books/" + saved.getId())
                .exchange()
                .returnResult(BookDto.class)
                .getResponseBody()
                .blockFirst();

        assertThat(result)
                .usingRecursiveComparison()
                .ignoringFields("authorDto")
                .isEqualTo(expected);
    }
}
