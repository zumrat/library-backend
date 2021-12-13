package lv.tsi.library.library.mapper;

import lv.tsi.library.library.dto.CheckOutDto;
import lv.tsi.library.library.entity.CheckOut;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CheckOutMapper {

    private final BookMapper bookMapper;

    public CheckOutMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public CheckOut toEntity(CheckOutDto dto) {
        return new CheckOut()
                .setId(dto.getId())
                .setReturnDate(dto.getReturnDate())
                .setDueDate(dto.getDueDate())
                .setReserveDate(LocalDate.now());
    }

    public CheckOutDto toDto(CheckOut entity) {
        return new CheckOutDto()
                .setId(entity.getId())
                .setBook(bookMapper.toDto(entity.getBook()))
                .setDueDate(entity.getDueDate())
                .setReturnDate(entity.getReturnDate())
                .setReserveDate(entity.getReserveDate());
    }
}
