package lv.tsi.library.library.service;

import lv.tsi.library.library.commontype.BookStatus;
import lv.tsi.library.library.dto.CheckOutDto;
import lv.tsi.library.library.exception.LibraryBadRequestException;
import lv.tsi.library.library.exception.LibraryEntityNotFoundException;
import lv.tsi.library.library.mapper.CheckOutMapper;
import lv.tsi.library.library.mapper.ReaderMapper;
import lv.tsi.library.library.repository.BookRepository;
import lv.tsi.library.library.repository.CheckOutRepository;
import lv.tsi.library.library.repository.ReaderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CheckOutService {

    private final BookRepository bookRepository;
    private final CheckOutRepository checkOutRepository;
    private final ReaderRepository readerRepository;
    private final CheckOutMapper checkOutMapper;
    private final ReaderMapper readerMapper;

    public CheckOutService(BookRepository bookRepository, CheckOutRepository checkOutRepository, ReaderRepository readerRepository, CheckOutMapper checkOutMapper, ReaderMapper readerMapper) {
        this.bookRepository = bookRepository;
        this.checkOutRepository = checkOutRepository;
        this.readerRepository = readerRepository;
        this.checkOutMapper = checkOutMapper;
        this.readerMapper = readerMapper;
    }

    public CheckOutDto borrowBook(CheckOutDto request) {
        var book = bookRepository.findById(request.getBook().getId())
                .orElseThrow(LibraryEntityNotFoundException.withMessage(String.format("Book with id=%s does not exist", request.getBook().getId())));
        if (BookStatus.BORROWED.equals(book.getBookStatus())) {
            throw new LibraryBadRequestException(String.format("Book with id=%s is already borrowed", request.getBook().getId()));
        }
        book.setBookStatus(BookStatus.BORROWED);
        var reader = readerRepository.findAllByFullNameContainingIgnoreCase(request.getReader().getFullName())
                .stream().findFirst()
                .orElseGet(() -> readerMapper.toEntity(request.getReader()));
        var savedReader = readerRepository.save(reader);
        var checkOut = checkOutMapper.toEntity(request);
        checkOut.setBook(book);
        checkOut.setReader(savedReader);

        bookRepository.save(book);
        var saved = checkOutRepository.save(checkOut);
        return checkOutMapper.toDto(saved).setReader(readerMapper.toDto(saved.getReader(), false));
    }

    public CheckOutDto returnBook(CheckOutDto request) {
        var book = bookRepository.findById(request.getBook().getId())
                .orElseThrow(LibraryEntityNotFoundException.withMessage(String.format("Book with id=%s does not exist", request.getBook().getId())));
        if (BookStatus.AVAILABLE.equals(book.getBookStatus())) {
            throw new LibraryBadRequestException(String.format("Book with id=%s is already available", request.getBook().getId()));
        }
        book.setBookStatus(BookStatus.AVAILABLE);

        var checkOut = checkOutRepository.findFirstByBookIdOrderByIdDesc(request.getBook().getId())
                .orElseThrow(LibraryEntityNotFoundException.withMessage(String.format("Check out with id=%s does not exist", request.getId())));
        checkOut.setReturnDate(LocalDate.now());

        bookRepository.save(book);
        var saved = checkOutRepository.save(checkOut);
        return checkOutMapper.toDto(saved).setReader(readerMapper.toDto(saved.getReader(), false));
    }
}
