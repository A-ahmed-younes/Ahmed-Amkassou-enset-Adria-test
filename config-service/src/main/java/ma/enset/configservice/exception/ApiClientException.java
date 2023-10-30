package ma.enset.core.exception;

import ma.enset.core.exception.dto.ExceptionResponse;
import lombok.Getter;

@Getter
public class ApiClientException extends RuntimeException {
    private final ExceptionResponse exception;

    public ApiClientException(ExceptionResponse exception) {
        super();
        this.exception = exception;
    }
}
