package lv.tsi.library.library.service;

import lv.tsi.library.library.dto.ReaderDto;
import lv.tsi.library.library.exception.LibraryEntityNotFoundException;
import lv.tsi.library.library.mapper.ReaderMapper;
import lv.tsi.library.library.repository.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final ReaderMapper readerMapper;

    public ReaderService(ReaderRepository readerRepository, ReaderMapper readerMapper) {
        this.readerRepository = readerRepository;
        this.readerMapper = readerMapper;
    }

    public ReaderDto findById(Long id) {
        var reader = readerRepository.findById(id)
                .orElseThrow(LibraryEntityNotFoundException.withMessage(String.format("Reader with id=%s does not exist", id)));
        return readerMapper.toDto(reader, true);
    }

    public List<ReaderDto> findAllByName(String name) {
        var readers = readerRepository.findAllByFullNameContainingIgnoreCase(name);
        return readers.stream()
                .map(it -> readerMapper.toDto(it, true))
                .collect(Collectors.toList());
    }

    public List<ReaderDto> getReadersList(){
        var readersList = readerRepository.findAll();
        return StreamSupport.stream(readersList.spliterator(), false)
                .map(it -> readerMapper.toDto(it, true))
                .collect(Collectors.toList());
    }

}

