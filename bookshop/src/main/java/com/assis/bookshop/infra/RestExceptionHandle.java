package com.assis.bookshop.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.assis.bookshop.excepetions.AlreadyRegisteredUser;
import com.assis.bookshop.excepetions.DeleteRecord;
import com.assis.bookshop.excepetions.FullException;
import com.assis.bookshop.excepetions.NotFoundException;

@ControllerAdvice
public class RestExceptionHandle extends ResponseEntityExceptionHandler{

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<RestErrorMessage> userNotFoundHandle(NotFoundException exception){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    } 

    
    @ExceptionHandler(FullException.class)
    private ResponseEntity<RestErrorMessage> UserFullErrorHandler(FullException exception){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatResponse);
    } 

    @ExceptionHandler(AlreadyRegisteredUser.class)
    private ResponseEntity<RestErrorMessage> AlreadyRegisteredUserHandler(AlreadyRegisteredUser exception){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_ACCEPTABLE,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(threatResponse);
    } 
    
    @ExceptionHandler(DeleteRecord.class)
    private ResponseEntity<RestErrorMessage> DeleteRecordHandler(DeleteRecord exception){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.OK,exception.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(threatResponse);
    } 
}
