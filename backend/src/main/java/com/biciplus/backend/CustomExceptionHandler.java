package com.biciplus.backend;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.biciplus.backend.controllers.util.Response;
import com.biciplus.backend.exceptions.FeatureDisabledException;

public class CustomExceptionHandler {
    
    @ExceptionHandler(FeatureDisabledException.class)
    public Response handleUnimplementedFeature(FeatureDisabledException exception) {
        return new Response(Response.Status.FEATURE_DISABLED, exception.getMessage());
    }
    
    @ExceptionHandler(Exception.class)
    public Response handleError(Exception exception) {
    	if(exception.getCause() != null && exception.getCause() instanceof FeatureDisabledException) return new Response(Response.Status.FEATURE_DISABLED, exception.getCause().getMessage());
        return new Response(Response.Status.ERROR, exception.getMessage());
    }
}
