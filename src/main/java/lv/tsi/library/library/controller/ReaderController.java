package lv.tsi.library.library.controller;

import lv.tsi.library.library.dto.ReaderDto;
import lv.tsi.library.library.service.ReaderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reader")
public class ReaderController {

    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping("/{id}")
    public ReaderDto getReaderById(@PathVariable Long id) {
        return readerService.findById(id);
    }

    @GetMapping("/search")
    public List<ReaderDto> findByName(@RequestParam String name) {
        return readerService.findAllByName(name);
    }

    @GetMapping("/list")
    public List<ReaderDto> findAll() {
        return readerService.getReadersList();
    }

}
