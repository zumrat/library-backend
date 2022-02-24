package lv.tsi.library.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.tsi.library.library.dto.BookDto;
import lv.tsi.library.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @Test
    void testGetBooks() throws Exception{
        List<BookDto> dtos = List.of(BookDto.builder().build());
        var expected = objectMapper.writeValueAsString(dtos);

        when(bookService.getBooks(null))
                .thenReturn(dtos);

        var result = mvc.perform(get("/api/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(result).isEqualTo(expected);
        verify(bookService).getBooks(null);
    }

    @Test
    void testGetBookById() throws Exception {
        var id = 1L;
        BookDto dto = BookDto.builder().build();
        var expected = objectMapper.writeValueAsString(dto);

        when(bookService.findById(id))
                .thenReturn(dto);

        var result = mvc.perform(get("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(result).isEqualTo(expected);
        verify(bookService).findById(id);
    }

    @Test
    void testFindBooksByString() throws Exception {
        var query = "query";
        List<BookDto> dtos = List.of(BookDto.builder().build());
        var expected = objectMapper.writeValueAsString(dtos);

        when(bookService.getBooks(query))
                .thenReturn(dtos);

        var result = mvc.perform(get("/api/books/search")
                        .queryParam("query", query)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(result).isEqualTo(expected);
        verify(bookService).getBooks(query);
    }

    @Test
    void testGetBooksSortedByNew() throws Exception {
        List<BookDto> dtos = List.of(BookDto.builder().build());
        var expected = objectMapper.writeValueAsString(dtos);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        when(bookService.getBooksSorted(sort))
                .thenReturn(dtos);

        var result = mvc.perform(get("/api/books/new")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(result).isEqualTo(expected);
        verify(bookService).getBooksSorted(sort);
    }

    @Test
    void testGetBooksSortedByCheckOutCount() throws Exception{
        List<BookDto> dtos = List.of(BookDto.builder().build());
        var expected = objectMapper.writeValueAsString(dtos);

        Sort sort = Sort.by(Sort.Direction.DESC, "checkOuts");
        when(bookService.getBooksSorted(sort))
                .thenReturn(dtos);

        var result = mvc.perform(get("/api/books/popular")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(result).isEqualTo(expected);
        verify(bookService).getBooksSorted(sort);
    }

    @Test
    void testCreateBook() throws Exception{
        BookDto dto = BookDto.builder().build();
        var expected = objectMapper.writeValueAsString(dto);

        when(bookService.createBook(dto))
                .thenReturn(dto);

        var result = mvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expected))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(result).isEqualTo(expected);
        verify(bookService).createBook(dto);

    }

    @Test
    void testDeleteBook() throws Exception {
        var id = 1l;

        mvc.perform(delete("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(bookService).deleteBook(id);

    }
}