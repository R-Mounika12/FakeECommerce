package com.example.FakeECommerce.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {

    private boolean success;

    private String message;

    private String error;

    private T data;

    public static <T>ApiResponse<T> success(T data, String message) {
        return  ApiResponse.<T>builder()
                .success(Boolean.TRUE)
                .message(message)
                .data(data)
                .build();
    }

    public static <T>ApiResponse<T> error(String error, String message) {
        return ApiResponse.<T>builder()
                .success(Boolean.FALSE)
                .message(message)
                .error(error)
                .build();
    }


}
