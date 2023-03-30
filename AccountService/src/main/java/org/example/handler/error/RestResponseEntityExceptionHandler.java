package org.example.handler.error;

import org.example.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<ErrorDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                    HttpServletRequest request) {
        List<String> errorList = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessages(errorList);
        errorDto.setStatus(HttpStatus.BAD_REQUEST);
        errorDto.setError(400);
        errorDto.setTimestamp(new Timestamp(new Date().getTime()));
        errorDto.setPath(request.getServletPath());

        return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ErrorDto> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessages(Arrays.asList("Bad Parameters"));
        errorDto.setStatus(HttpStatus.BAD_REQUEST);
        errorDto.setError(400);
        errorDto.setTimestamp(new Timestamp(new Date().getTime()));
        errorDto.setPath(request.getServletPath());

        return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
