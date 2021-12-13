package lv.tsi.library.library.repository;

import lv.tsi.library.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllDistinctByAuthorFullNameContainingIgnoreCaseOrTitleContainingIgnoreCaseOrBookGenresGenreValueContainingIgnoreCase(String authorName, String title, String genre);


}
