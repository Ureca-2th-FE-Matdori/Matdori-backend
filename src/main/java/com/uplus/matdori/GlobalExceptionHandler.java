package com.uplus.matdori;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 모든 컨트롤러에서 발생하는 예외를 처리하는 글로벌 예외 핸들러
public class GlobalExceptionHandler {
  // 사용자 정의 예외 처리 (예: RuntimeException)
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
    ErrorResponse response = new ErrorResponse(null, ex.getMessage()); // JSON 응답 객체 생성
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  // 기타 예외 처리 (예: NullPointerException, IllegalArgumentException 등)
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception ex) {
    ErrorResponse response = new ErrorResponse(null, "서버 내부 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  // JSON 응답을 위한 DTO (내부 클래스)
  @Getter
  @AllArgsConstructor
  static class ErrorResponse {
    private Object content; // 실패 시에는 null
    private String message; // 오류 메시지
  }
}
