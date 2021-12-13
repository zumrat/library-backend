package lv.tsi.library.library.controller;

import lv.tsi.library.library.dto.CheckOutDto;
import lv.tsi.library.library.service.CheckOutService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout")
public class CheckOutController {

    private final CheckOutService checkOutService;

    public CheckOutController(CheckOutService checkOutService) {
        this.checkOutService = checkOutService;
    }

    @PostMapping("/borrow")
    public CheckOutDto borrowBook(@RequestBody @Validated CheckOutDto request) {
        return checkOutService.borrowBook(request);
    }

    @PostMapping("/return")
    public CheckOutDto returnBook(@RequestBody CheckOutDto request) {
        return checkOutService.returnBook(request);
    }
}
