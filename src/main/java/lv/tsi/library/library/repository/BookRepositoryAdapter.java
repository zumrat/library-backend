package lv.tsi.library.library.repository;

import lv.tsi.library.library.entity.Book;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookRepositoryAdapter implements JpaRepository<Book, Long> {
    private final BookRepository bookRepository;

    public BookRepositoryAdapter(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> searchBooks(String query) {
        return bookRepository.findAllDistinctByAuthorFullNameContainingIgnoreCaseOrTitleContainingIgnoreCaseOrBookGenresGenreValueContainingIgnoreCase(query, query, query);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findAll(Sort sort) {
        return bookRepository.findAll(sort);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public List<Book> findAllById(Iterable<Long> longs) {
        return bookRepository.findAllById(longs);
    }

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    public <S extends Book> S save(S entity) {
        return bookRepository.save(entity);
    }

    @Override
    public <S extends Book> List<S> saveAll(Iterable<S> entities) {
        return bookRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        bookRepository.flush();
    }

    @Override
    public <S extends Book> S saveAndFlush(S entity) {
        return bookRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends Book> List<S> saveAllAndFlush(Iterable<S> entities) {
        return bookRepository.saveAllAndFlush(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Book> entities) {
        bookRepository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        bookRepository.deleteAllByIdInBatch(longs);
    }

    @Override
    public void deleteAllInBatch() {
        bookRepository.deleteAllInBatch();
    }

    @Override
    public Book getOne(Long aLong) {
        return bookRepository.getOne(aLong);
    }

    @Override
    public Book getById(Long aLong) {
        return bookRepository.getById(aLong);
    }

    @Override
    public <S extends Book> Optional<S> findOne(Example<S> example) {
        return bookRepository.findOne(example);
    }

    @Override
    public <S extends Book> List<S> findAll(Example<S> example) {
        return bookRepository.findAll(example);
    }

    @Override
    public <S extends Book> List<S> findAll(Example<S> example, Sort sort) {
        return bookRepository.findAll(example, sort);
    }

    @Override
    public <S extends Book> Page<S> findAll(Example<S> example, Pageable pageable) {
        return bookRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Book> long count(Example<S> example) {
        return bookRepository.count(example);
    }

    @Override
    public <S extends Book> boolean exists(Example<S> example) {
        return bookRepository.exists(example);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return bookRepository.existsById(aLong);
    }


    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void delete(Book entity) {
        bookRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        bookRepository.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Book> entities) {
        bookRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }
}
