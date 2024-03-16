package org.scaler.ecomorderservice.controlleradvices;

import org.scaler.ecomorderservice.dtos.ExceptionDto;
import org.scaler.ecomorderservice.exceptions.OrderDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(value = OrderDoesNotExistException.class)
    public ResponseEntity<ExceptionDto> handleOrderDoesNotExist(OrderDoesNotExistException exception) {
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(exception.getMessage());
        dto.setDetail("Check the order id. It probably doesn't exist.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }
}
