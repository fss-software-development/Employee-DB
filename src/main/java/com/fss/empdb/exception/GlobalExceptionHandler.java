package com.fss.empdb.exception;

import com.fss.empdb.util.ExceptionHandlerValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionResponse> globleExcpetionHandler(Exception ex, WebRequest request) {
//        System.out.println("Exception :" + ex.toString());
//        String exceptionType = ExceptionHandlerValidation.NullCheck(ex);
//        System.out.println("ExceptionType :" + exceptionType);
//        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exceptionType, request.getDescription(false), HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
//        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleAnyException(WebRequest request, NullPointerException ex) {
        String errorMessageDescription = ex.getLocalizedMessage();
        if (errorMessageDescription == null)
            errorMessageDescription = ex.toString();

        ErrorDetails errorDetails = new ErrorDetails(new Date(), "NullPointerException", request.getDescription(false));

        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Object> handleArithmeticExceptionError(WebRequest request, ArithmeticException ex) {
        String errorMessageDescription = ex.getLocalizedMessage();
        if (errorMessageDescription == null)
            errorMessageDescription = ex.toString();

        ErrorDetails errorDetails = new ErrorDetails(new Date(), "ArithmeticException", request.getDescription(false));

        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Object> handleArrayIndexOutOfBoundsExceptionError(WebRequest request, ArrayIndexOutOfBoundsException ex) {
        String errorMessageDescription = ex.getLocalizedMessage();
        if (errorMessageDescription == null)
            errorMessageDescription = ex.toString();

        ErrorDetails errorDetails = new ErrorDetails(new Date(), "ArrayIndexOutOfBoundsException", request.getDescription(false));

        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Object> handleSQLExceptionError(WebRequest request, FileNotFoundException ex) {
        String errorMessageDescription = ex.getLocalizedMessage();
        if (errorMessageDescription == null)
            errorMessageDescription = ex.toString();

        ErrorDetails errorDetails = new ErrorDetails(new Date(), "FileNotFoundException", request.getDescription(false));

        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSQLExceptionError(WebRequest request, SQLException ex) {
        String errorMessageDescription = ex.getLocalizedMessage();
        if (errorMessageDescription == null)
            errorMessageDescription = ex.toString();

        ErrorDetails errorDetails = new ErrorDetails(new Date(), "SQLException", request.getDescription(false));


        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<ExceptionResponse> duplicateRecordException(DuplicateRecordException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
