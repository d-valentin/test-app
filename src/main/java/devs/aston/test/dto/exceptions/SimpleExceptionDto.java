package devs.aston.test.dto.exceptions;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SimpleExceptionDto {

    private String value;

    private String message;

    private Timestamp timestamp;

}
