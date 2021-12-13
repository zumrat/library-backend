package lv.tsi.library.library.repository;

import lv.tsi.library.library.entity.CheckOut;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckOutRepository extends CrudRepository<CheckOut, Long> {
    Optional<CheckOut> findFirstByBookIdOrderByIdDesc(Long bookId);
}
