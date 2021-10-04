package com.example.employee.error;

import com.example.employee.model.ErrorCode;
import com.example.employee.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.employee.model.ErrorCode.EMPLOYEE_NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({EmployeeNotFoundException.class})
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleEmployeeNotFoundException(EmployeeNotFoundException exception) {
        return logErrorAndRespond(EMPLOYEE_NOT_FOUND, exception.getMessage());
    }

    private ErrorResponse logErrorAndRespond(ErrorCode errorCode, String errorMessage) {
        ErrorResponse errorResponse = new ErrorResponse(UUID.randomUUID().toString(), errorCode, errorMessage, LocalDateTime.now());
        String logMessage = "Request failed at %s with following error %s";
        log.error(String.format(logMessage, LocalDateTime.now(), errorResponse.toString()));
        return errorResponse;
    }
}
