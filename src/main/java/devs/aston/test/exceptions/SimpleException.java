package devs.aston.test.exceptions;

import devs.aston.test.enums.Errors;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = false)
public class SimpleException extends RuntimeException {

    private String value;

    private String message;

    private Timestamp timestamp;

    public static SimpleException with(String value, Errors error) {
        SimpleException exception = new SimpleException();
        exception.setValue(value);
        exception.setMessage(error.getMessage());
        exception.setTimestamp(Timestamp.from(Instant.now()));
        return exception;
    }

}
