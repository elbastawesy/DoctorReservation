package com.bastawesy.doctor_reservation.util.exception;

import com.bastawesy.core.util.excpetion.ErrorDetails;
import com.bastawesy.doctor_reservation.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.system.SystemProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sun.reflect.Reflection;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Custom exception handling class to handle all exception resulted from
 */

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    protected final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
    public static final String DEFAULT_ERROR_MESSAGE = "Error processing your request";
    public static final String HTTP_MESSAGE_NOT_READABLE_EXCEPTION_MESSAGE = "Invalid Json request";
    public static final String JSON_PARSE_EXCEPTION_MESSAGE = "Invalid json request";

    // determine whether to show full stack trace in rest error response or not.
    @Value("${app.exception-handling.rest-response.show-stack-trace}")
    private boolean showStackTraceAtResponse;

    /**
     * @param ex      {@link MethodArgumentNotValidException}
     * @param headers {@link HttpHeaders
     * @param status  {@link HttpStatus}
     * @param request web request that threw the exception
     * @return ResponseEntity object holding the created errorDetails
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        String message = CommonUtils.getObjectAsString(errors);
        String developerMessage = ex.getClass().getSimpleName() + " : " + message;
        logException(developerMessage, ex);
        return createErrorDetailsResponseEntity(request, message, developerMessage, headers, ex);
    }

    /**
     * Handle EntityNotFoundException exception
     *
     * @param exception EntityNotFoundException exception
     * @param request   web request that threw the exception
     * @return {@link ErrorDetails} instance
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDetails handleEntityNotFoundException(EntityNotFoundException exception, WebRequest request) {
        String developerMessage = "EntityNotFoundException:  " + exception.getMessage();
        logger.error(developerMessage, exception);
        return buildErrorDetails(request, DEFAULT_ERROR_MESSAGE, developerMessage, HttpStatus.NOT_FOUND,
                exception);
    }

    /**
     * Handle ConstraintViolationException exception
     *
     * @param exception ConstraintViolationException exception
     * @param request   web request that threw the exception
     * @return {@link ErrorDetails} instance
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorDetails handleValidationException(ConstraintViolationException exception, WebRequest request) {
        StringBuilder exDetail = new StringBuilder();
        exception.getConstraintViolations().forEach(constraintValidation -> exDetail
//                        .append(constraintValidation.getPropertyPath().toString()).append(" : ")
                .append(constraintValidation.getMessage()).append(System.getProperty("line.separator")));
        String developerMessage = "ConstraintViolationException:  " + exception.getMessage();
        logException(developerMessage, exception);
        return buildErrorDetails(request, exDetail.toString(), developerMessage, HttpStatus.BAD_REQUEST, exception);
    }

    /**
     * Handle ResponseStatusException exception
     *
     * @param exception ResponseStatusException exception
     * @param request   web request that threw the exception
     * @return {@link ErrorDetails} instance
     */
    @ExceptionHandler(ResponseStatusException.class)
    public final ResponseEntity<ErrorDetails> handleResponseStatusException(ResponseStatusException exception, WebRequest request) {
        String developerMessage = "ResponseStatusException:  " + exception.getMessage();
        logger.error(developerMessage, exception);
        HttpStatus exceptionStatus = exception.getStatus();
        if (CommonUtils.isBlankOrNull(exceptionStatus)) {
            exceptionStatus = HttpStatus.BAD_REQUEST;
        }
        ErrorDetails errorDetails = buildErrorDetails(request, exception.getReason(), developerMessage, exceptionStatus, exception);
        return new ResponseEntity<>(errorDetails, exceptionStatus);
    }

    /**
     * Handle JsonParseException exception
     *
     * @param ex      JsonParseException exception
     * @param request web request that threw the exception
     * @return {@link ErrorDetails} instance
     */
    @ExceptionHandler(value = {JsonParseException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorDetails handleJsonProcessingException(JsonParseException ex, WebRequest request) {
        String developerMessage = "JsonParseException:  " + ex.getMessage();
        logException(developerMessage, ex);
        return buildErrorDetails(request, JSON_PARSE_EXCEPTION_MESSAGE, developerMessage,
                HttpStatus.BAD_REQUEST, ex);
    }

    /**
     * @param ex      thrown exception
     * @param headers {@link HttpHeaders}
     * @param status  {@link HttpStatus}
     * @param request web request that threw the exception
     * @return ResponseEntity object holding the created errorDetails
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ex.getRootCause();
        String errorDetails = CommonUtils.isBlankOrNull(rootCause) ? ex.getMessage() : rootCause.getMessage();
        String developerMessage = ex.getClass().getSimpleName() + " : " + errorDetails;
        logException(developerMessage, ex);
        return createErrorDetailsResponseEntity(request, HTTP_MESSAGE_NOT_READABLE_EXCEPTION_MESSAGE,
                developerMessage, headers, ex);
    }

    /**
     * Handle BAD_REQUEST exception
     *
     * @param ex      BAD_REQUEST exception
     * @param request web request that threw the exception
     * @return {@link ErrorDetails} instance
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorDetails handleAllExceptions(Exception ex, WebRequest request) {
        String developerMessage = ex.getClass().getSimpleName() + " : " + ex.getMessage();
        logException(developerMessage, ex);
        return buildErrorDetails(request, DEFAULT_ERROR_MESSAGE, ex.getMessage(), HttpStatus.BAD_REQUEST, ex);
    }

    /**
     * Log thrown exception
     *
     * @param errorMessage log error message
     * @param exception    exception to be logged
     */
    private void logException(String errorMessage, Exception exception) {
        logger.error(errorMessage, exception);
    }

    /**
     * create new ResponseEntity holding the error detail for exception
     *
     * @param request          web request that threw the exception
     * @param message          message to be shown in UI
     * @param developerMessage technical message to be communicated between the
     *                         caller and controller's developer.
     * @param status           {@link HttpStatus}
     * @return {@link ErrorDetails} instance
     */
    private ErrorDetails buildErrorDetails(WebRequest request, String message, String developerMessage,
                                           HttpStatus status, Exception exception) {
        ErrorDetails.ErrorDetailsBuilder errorDetailsBuilder = ErrorDetails.builder()
                .timestamp(System.currentTimeMillis()).httpStatus(status).httpStatusCode(status.value())
                .message(message).developerMessage(developerMessage).details(request.getDescription(false))
                .requestStatus(ErrorDetails.RequestStatus.FAILED);

        if (showStackTraceAtResponse) {
            // add full stack trace to make debugging easier
            errorDetailsBuilder.stackTrace(CommonUtils.getStackTraceAsString(exception));
        }
        return errorDetailsBuilder.build();
    }

    /**
     * Create {@link ResponseEntity} that hold the created {@link ErrorDetails}
     * object with status {@link HttpStatus#BAD_REQUEST} and
     *
     * @param request          web request that threw the exception
     * @param message          message to be shown in UI
     * @param developerMessage technical message to be communicated between the
     *                         caller and controller's developer.
     * @param headers          {@link HttpHeaders}
     * @param exception        throw exception
     * @return ResponseEntity object holding the created errorDetails
     */
    private ResponseEntity<Object> createErrorDetailsResponseEntity(WebRequest request, String message,
                                                                    String developerMessage, HttpHeaders headers, Exception exception) {
        return createErrorDetailsResponseEntity(request, message, developerMessage, headers, HttpStatus.BAD_REQUEST,
                exception);
    }

    /**
     * * Create {@link ResponseEntity} that hold the created {@link ErrorDetails}
     * object with passed {@link HttpStatus}
     *
     * @param request          web request that threw the exception
     * @param message          message to be shown in UI
     * @param developerMessage technical message to be communicated between the
     *                         caller and controller's developer.
     * @param headers          {@link HttpHeaders}
     * @param status           {@link HttpStatus}
     * @param exception        throw exception
     * @return ResponseEntity object holding the created errorDetails
     */
    private ResponseEntity<Object> createErrorDetailsResponseEntity(WebRequest request, String message,
                                                                    String developerMessage, HttpHeaders headers, HttpStatus status, Exception exception) {
        ErrorDetails errorDetails = buildErrorDetails(request, message, developerMessage, status, exception);
        return new ResponseEntity<>(errorDetails, headers, status);
    }

}
