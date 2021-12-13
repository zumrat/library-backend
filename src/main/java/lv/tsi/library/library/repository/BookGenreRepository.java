package lv.tsi.library.library.repository;

import lv.tsi.library.library.entity.BookGenre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookGenreRepository extends CrudRepository<BookGenre, Long> {
    void deleteAllByBookIdAndGenreValueNotIn(Long bookId, List<String> genres);
}
