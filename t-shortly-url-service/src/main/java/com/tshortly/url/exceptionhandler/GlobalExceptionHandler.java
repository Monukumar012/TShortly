package com.tshortly.url.exceptionhandler;
import com.tshortly.framework.api.ApiMessage;
import com.tshortly.framework.api.ApiMessageFactory;
import com.tshortly.framework.api.ApiResponse;
import com.tshortly.framework.expcetion.EntityNotFoundException;
import com.tshortly.url.exception.AliasNotAvailableException;
import com.tshortly.url.exception.UrlNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ApiMessageFactory apiMessageFactory;

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleUrlNotFoundException(UrlNotFoundException ex) {
        ApiMessage error = apiMessageFactory.of("url.not.found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.failure(error));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiMessage error = apiMessageFactory.of("entity.not.found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.failure(error));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.failure(apiMessageFactory.defaultError()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        List<ApiMessage> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> ApiMessage.of(fieldError.getCode(), fieldError.getDefaultMessage())).toList();
        return ResponseEntity.badRequest().body(ApiResponse.failure(errors));
    }

    @ExceptionHandler(AliasNotAvailableException.class)
    public ResponseEntity<ApiResponse<?>> handleAliasNotAvailableException(AliasNotAvailableException ex) {
        ApiMessage error = apiMessageFactory.of("shortUrl.alias.notAvailable");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.failure(error));
    }
}