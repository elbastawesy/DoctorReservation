package com.bastawesy.core.util.excpetion;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class ErrorDetails implements Serializable {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long timestamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int httpStatusCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private HttpStatus httpStatus;
    // 1 for success and 0 for fail
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RequestStatus requestStatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String developerMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String details;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String stackTrace;

    public enum RequestStatus {
        SUCCESS(1), FAILED(0);
        private int value;

        RequestStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
