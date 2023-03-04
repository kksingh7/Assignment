package com.assignment.taskManager.controller.Serializer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.util.Collection;

public class TaskResponseEntity<T> extends ResponseEntity implements Serializable {
    private static final long serialVersionUID = 7156526077883281625L;

    public TaskResponseEntity(HttpStatus status) {
        super(status);
    }

    public TaskResponseEntity(Object body, HttpStatus status) {
        super(body, status);
    }

    public TaskResponseEntity(MultiValueMap headers, HttpStatus status) {
        super(headers, status);
    }

    public TaskResponseEntity(Object body, MultiValueMap headers, HttpStatus status) {
        super(body, headers, status);
    }
}
