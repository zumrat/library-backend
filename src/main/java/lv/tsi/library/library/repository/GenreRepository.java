package lv.tsi.library.library.repository;

import lv.tsi.library.library.entity.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findAllByValueIn(List<String> genres);
}
