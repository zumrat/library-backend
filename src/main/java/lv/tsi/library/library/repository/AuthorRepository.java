package lv.tsi.library.library.repository;

import lv.tsi.library.library.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> findByFullName(String fullName);
}
