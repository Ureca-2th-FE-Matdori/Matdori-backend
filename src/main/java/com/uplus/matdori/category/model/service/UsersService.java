package com.uplus.matdori.category.model.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uplus.matdori.category.model.dto.ApiResponse;
import com.uplus.matdori.category.model.dto.UserDTO;
import com.uplus.matdori.category.model.dto.UserResponseDto;

//회원정보 관련 비즈니스 로직 처리 (interface)
@Service
public interface UsersService {
    boolean login(String userId, String password);
    
    public ResponseEntity<ApiResponse<UserResponseDto>> createAccount(UserDTO userDTO);
     
    boolean checkUserId(String userId);
    
}
