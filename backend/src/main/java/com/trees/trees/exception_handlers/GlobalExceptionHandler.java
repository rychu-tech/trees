package com.trees.trees.exception_handlers;

import com.trees.trees.features.tree_structure.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
            TreeNameExistsException.class,
            TreeNameIsEmptyException.class,
            TreeIdNotExistsException.class,
            NodeDoesNotBelongToTreeException.class,
            NodeIdNotExistsException.class,
            RootNodeCannotBeDeletedException.class
    })
    public ResponseEntity<ErrorResponse> handleCustomException(Exception ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), status.value());
        return new ResponseEntity<>(errorResponse, status);
    }
}
