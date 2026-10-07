package com.example.FakeECommerce.exception;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

@Component
public class ExceptionHelper {

    public BadRequestException badRequestException(String message) {
        return new BadRequestException(message);
    }
}
