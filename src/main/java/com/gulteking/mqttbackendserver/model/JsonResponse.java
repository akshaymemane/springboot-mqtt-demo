package com.gulteking.mqttbackendserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class JsonResponse {
    private Object data;
    private String message;
    private Integer statusCode;
    private HttpStatus status;
}
