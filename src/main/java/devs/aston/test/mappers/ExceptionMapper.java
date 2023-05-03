package devs.aston.test.mappers;

import devs.aston.test.dto.exceptions.SimpleExceptionDto;
import devs.aston.test.exceptions.SimpleException;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExceptionMapper {

    SimpleExceptionDto toDto(SimpleException e);

}
