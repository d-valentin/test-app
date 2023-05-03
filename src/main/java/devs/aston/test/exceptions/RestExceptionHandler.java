package devs.aston.test.exceptions;

import devs.aston.test.dto.exceptions.SimpleExceptionDto;
import devs.aston.test.mappers.ExceptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {

    private final ExceptionMapper mapper;

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SimpleException.class)
    public SimpleExceptionDto process(SimpleException e) {
        return mapper.toDto(e);
    }
}
