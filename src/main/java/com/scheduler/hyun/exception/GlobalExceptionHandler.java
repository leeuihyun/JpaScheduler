package com.scheduler.hyun.exception;

import com.scheduler.hyun.domain.dto.common.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    // 커스텀
    @ExceptionHandler(ScheduleException.class)
    public ResponseEntity<ErrorResponse> handleRequestBodyException(
        ScheduleException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
            .body(new ErrorResponse(ex.getHttpStatus(), ex.getErrorMessage()));
    }

    //@Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
        MethodArgumentNotValidException ex) {

        FieldError error = ex.getBindingResult().getFieldErrors().get(0);
        ErrorResponse errorResponse = new ErrorResponse(400, error.getDefaultMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    //@Validated
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleParamException(
        ConstraintViolationException ex) {

        ConstraintViolation<?> violation = ex.getConstraintViolations().iterator().next();
        ErrorResponse errorResponse = new ErrorResponse(400, violation.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
