package com.example.biling_system.exception;


import com.example.biling_system.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ApiResponse> AppExceptionHandler(AppException e) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(e.getErrorCode().getCode());
        apiResponse.setMessage(e.getErrorCode().getMessage());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        if (fieldError == null) {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setCode(ErrorCode.NOT_BLANK.getCode());
            apiResponse.setMessage("Validation error occurred.");
            return ResponseEntity.badRequest().body(apiResponse);
        }
        String enumkey = fieldError.getDefaultMessage();
        ErrorCode errorCode;
        try {
            errorCode = ErrorCode.valueOf(enumkey);
        } catch (IllegalArgumentException ex) {
            errorCode = ErrorCode.NOT_BLANK;
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException e) {
        ApiResponse apiResponse = new ApiResponse<>();
        ErrorCode errorCode = ErrorCode.valueOf(e.getCause().getMessage());
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
