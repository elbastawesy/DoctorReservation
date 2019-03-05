package com.bastawesy.doctor_reservation.util.exception;

import com.bastawesy.core.util.excpetion.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.system.SystemProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sun.reflect.Reflection;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Date;

/**
 * Custom exception handling class to handle all exception resulted from
 */

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    protected final Logger logger = LoggerFactory.getLogger(Reflection.getCallerClass(4).getName());

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDetails handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        logger.error("Exception: Unable to process this request. ", ex);
        return createErrorDetailResponseEntity(request, ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorDetails handleValidationException(ConstraintViolationException ex, WebRequest request) {
        StringBuilder exDetail= new StringBuilder();
        ex.getConstraintViolations().forEach(constraintValidation->exDetail.append(constraintValidation.getMessage()).append(System.getProperty("line.separator")));
        logger.error("Exception: Unable to process this request. ", ex);
        return createErrorDetailResponseEntity(request, exDetail.toString(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {JsonParseException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorDetails handleJsonProcessingException(JsonParseException ex, WebRequest request) {
        logger.error("Exception: Unable to process this request. ", ex);
        String message = ex.getMessage();
        return createErrorDetailResponseEntity(request, message, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Exception: Unable to process this request. ", ex);
        ErrorDetails errorDetailResponseEntity = createErrorDetailResponseEntity(request, ex.getRootCause().getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorDetailResponseEntity, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorDetails handleAllExceptions(Exception ex, WebRequest request) {
        logger.error("Exception: Unable to process this request. >>>>>>>>>>> ", ex);
        return createErrorDetailResponseEntity(request, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * create new ResponseEntity holding the error detail for exception
     *
     * @param request
     * @param errorDetail
     * @param status
     * @return
     */
    private ErrorDetails createErrorDetailResponseEntity(WebRequest request, String errorDetail, HttpStatus status) {
        return new ErrorDetails(new Date(), status.value(), status, 0, errorDetail, request.getDescription(false));
    }
}
