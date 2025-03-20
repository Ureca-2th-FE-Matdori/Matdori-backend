package com.uplus.matdori.category.model.dto;


import java.util.HashMap;
import java.util.Optional;

public class ApiResponse<T> {
    private T content;
    private String message;

    public ApiResponse(T content, String message) {
        this.content = content;
        this.message = message;
    }

    //성공했을 때
    public static <T> ApiResponse<T> success(T content) {
        return new ApiResponse<>(content, null);
    }

    //실패했을 때
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(null, message);
    }

    public T getContent() {
        return content;
    }

    public String getMessage() {
        return message;
    }
}
