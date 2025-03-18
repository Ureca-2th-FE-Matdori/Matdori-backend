package com.uplus.matdori.category.model.dto;

public class ApiResponse<T> {
    private T content;
    private String message;

    public ApiResponse(T content, String message) {
        this.content = content;
        this.message = message;
    }

    public static <T> ApiResponse<T> success(T content) {
        return new ApiResponse<>(content, null);
    }

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
