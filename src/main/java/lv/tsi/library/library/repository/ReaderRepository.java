package lv.tsi.library.library.repository;

import lv.tsi.library.library.entity.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReaderRepository extends CrudRepository<Reader, Long> {
    List<Reader> findAllByFullNameContainingIgnoreCase(String name);
}
