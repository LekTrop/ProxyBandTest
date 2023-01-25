package ua.zhytariuk.proxyband.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.zhytariuk.proxyband.models.api.ErrorApi;

/**
 * TODO: Change class description
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorApi> handleBadRequestException(final BadRequestException ex) {
        return new ResponseEntity<>(ErrorApi.builder()
                                            .errorCode(HttpStatus.BAD_REQUEST.value())
                                            .message(ex.getMessage())
                                            .build(),
                                    HttpStatus.BAD_REQUEST);
    }
}
