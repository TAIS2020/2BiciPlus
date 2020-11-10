package com.biciplus.backend;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.biciplus.backend.controllers.util.Response;

public class CustomExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Response handleError(Exception exception) {
        return new Response(Response.Status.ERROR, exception.getMessage());
    }
}
