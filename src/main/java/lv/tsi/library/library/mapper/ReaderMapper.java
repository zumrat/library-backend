package lv.tsi.library.library.mapper;

import lv.tsi.library.library.dto.ReaderDto;
import lv.tsi.library.library.entity.Reader;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ReaderMapper {

    private final CheckOutMapper checkOutMapper;

    public ReaderMapper(CheckOutMapper checkOutMapper) {
        this.checkOutMapper = checkOutMapper;
    }

    public Reader toEntity(ReaderDto dto) {
        return new Reader()
                .setAddress(dto.getAddress())
                .setEmail(dto.getEmail())
                .setFullName(dto.getFullName())
                .setPhoneNumber(dto.getPhoneNumber());
    }

    public ReaderDto toDto(Reader entity, boolean mapCheckOuts) {
        var dto = new ReaderDto()
                .setAddress(entity.getAddress())
                .setEmail(entity.getEmail())
                .setId(entity.getId())
                .setFullName(entity.getFullName())
                .setPhoneNumber(entity.getPhoneNumber());
        if (mapCheckOuts) {
            dto.setCheckOuts(entity.getCheckouts().stream()
                    .map(checkOutMapper::toDto)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}
