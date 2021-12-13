package lv.tsi.library.library.controller;

import lv.tsi.library.library.service.GenreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<String> getAllGenres() {
        return genreService.getAllGenres();
    }

    @PostMapping("/book/{id}")
    public List<String> updateGenres(@PathVariable Long id, @RequestBody List<String> genres) {
        return genreService.updateGenresForBook(id, genres);
    }
}
