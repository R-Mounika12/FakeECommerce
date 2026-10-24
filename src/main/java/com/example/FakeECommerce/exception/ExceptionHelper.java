package com.example.FakeECommerce.exception;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHelper {

    public BadRequestException badRequestException(String message) {
        return new BadRequestException(message);
    }

    public ResourceNotFoundException resourceNotFoundException(String message) {
        return new ResourceNotFoundException(message);
    }

    public ResourceDeletionException resourceDeletionException(String message) {
        return new ResourceDeletionException(message);
    }
}
