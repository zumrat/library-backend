package lv.tsi.library.library.mapper;

import lv.tsi.library.library.dto.AuthorDto;
import lv.tsi.library.library.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author toEntity(AuthorDto authorDto) {
        return new Author()
                .setFullName(authorDto.getFullName())
                .setId(authorDto.getId());
    }

    public AuthorDto toDto(Author author) {
        return AuthorDto.builder()
                .fullName(author.getFullName())
                .id(author.getId())
                .build();
    }
}
