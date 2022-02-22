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
        var builder = ReaderDto.builder()
                .address(entity.getAddress())
                .email(entity.getEmail())
                .id(entity.getId())
                .fullName(entity.getFullName())
                .phoneNumber(entity.getPhoneNumber());
        if (mapCheckOuts) {
            builder.checkOuts(entity.getCheckouts().stream()
                    .map(checkOutMapper::toDto)
                    .collect(Collectors.toList()));
        }
        return builder.build();
    }
}
