package com.guise.guiseproject.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.guise.guiseproject.dto.HttpResponse;
import com.guise.guiseproject.dto.ResponseMessage;
import com.guise.guiseproject.dto.ValidationError;
import com.guise.guiseproject.dto.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;



/**
 * The type Global exception handler. Register your custom exceptions as shown below. Global
 * Exception handler catches the exceptions and modifies the response our custom response structure.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  private static final org.slf4j.Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

  private static final String ERROR = "Error";

  /**
   * Handle bad request exception response entity.
   *
   * @param badRequestException the bad request exception
   * @param response the response
   * @return the response entity
   */
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity handleBadRequestException(
      BadRequestException badRequestException, HttpServletResponse response) {
    HttpResponse errorResponse =
        new HttpResponse(HttpStatus.BAD_REQUEST, badRequestException.getMessage());

    // Customise the log format below according to the need
    LOGGER.error(badRequestException.getMessage(), badRequestException);
    return new ResponseEntity<>(new ResponseMessage(ERROR, errorResponse), HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle not found exception response entity.
   *
   * @param notFoundException the not found exception
   * @param response the response
   * @return the response entity
   */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity handleNotFoundException(
      NotFoundException notFoundException, HttpServletResponse response) {
    HttpResponse errorResponse =
        new HttpResponse(HttpStatus.NOT_FOUND, notFoundException.getMessage());

    // Customise the log format below according to the need
    LOGGER.error(notFoundException.getMessage(), notFoundException);
    return new ResponseEntity<>(new ResponseMessage("Error", errorResponse), HttpStatus.NOT_FOUND);
  }

  /**
   * Handle method argument not valid response entity.
   *
   * @param e the e
   * @param response the response
   * @return the response entity
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity handleMethodArgumentNotValid(
      MethodArgumentNotValidException e, HttpServletResponse response) {
    BindingResult bindingResult = e.getBindingResult();
    List<ValidationError> validationErrors =
        bindingResult.getFieldErrors().stream()
            .map(
                fieldError ->
                    new ValidationError(
                        fieldError.getField(),
                        fieldError.getCode(),
                        fieldError.getDefaultMessage()))
            .collect(Collectors.toList());

    // Customise the log format below according to the need
    LOGGER.error(e.getMessage(), e);
    return new ResponseEntity<>(
        new ValidationErrorResponse(ERROR, validationErrors), HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle method argument not valid response entity.
   *
   * @param errors the errors
   * @param response the response
   * @return the response entity
   */
  @ExceptionHandler(InvalidFormatException.class)
  public ResponseEntity handleMethodArgumentNotValid(
      InvalidFormatException errors, HttpServletResponse response) {

    List<ValidationError> validationErrorList =
        errors.getPath().stream()
            .map(
                error ->
                    new ValidationError(
                        error.getFieldName(),
                        HttpStatus.UNPROCESSABLE_ENTITY.toString(),
                        errors.getOriginalMessage()))
            .collect(Collectors.toList());

    // Customise the log format below according to the need
    LOGGER.error(errors.getMessage(), errors);
    return new ResponseEntity<>(
        new ValidationErrorResponse(ERROR, validationErrorList), HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler(FileFormatException.class)
  public ResponseEntity<ResponseMessage> handleFileFormatException(
      RuntimeException exception, HttpServletResponse response) {
    HttpResponse errorResponse =
        new HttpResponse(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());

    LOGGER.error(exception.getMessage(), exception);
    return new ResponseEntity<>(
        new ResponseMessage(ERROR, errorResponse), HttpStatus.NOT_ACCEPTABLE);
  }


}
