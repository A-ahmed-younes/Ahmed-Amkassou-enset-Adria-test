package ma.enset.configservice.exception;

import ma.enset.configservice.exception.dto.ExceptionResponse;
import lombok.Getter;

@Getter
public class ApiClientException extends RuntimeException {
    private final ExceptionResponse exception;

    public ApiClientException(ExceptionResponse exception) {
        super();
        this.exception = exception;
    }
}
