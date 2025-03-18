package com.uplus.matdori.category.model.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uplus.matdori.category.model.dao.UserDAO;
import com.uplus.matdori.category.model.dto.ApiResponse;
import com.uplus.matdori.category.model.dto.UserDTO;
import com.uplus.matdori.category.model.dto.UserResponseDto;

//회원 정보 관련 기능들을 구현한 UsersServiceImp
@Service
public class UsersServiceImp implements UsersService {
    private final UserDAO userDAO;
    
	
    public UsersServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean login(String userId, String password) {
        UserDTO user = userDAO.getUserById(userId);
        return user != null && user.getPassword().equals(password);
    }
   
    // 아이디 중복 확인
    public boolean checkUserId(String userId) {
    	UserDTO user = userDAO.getUserById(userId);
    	
    	if (user == null) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    
    // 회원가입
    public ResponseEntity<ApiResponse<UserResponseDto>> createAccount(UserDTO userDTO) {

    	if(checkUserId(userDTO.getUser_id())) {
    		// id가 null 값이 맞다면 가입 처리 진행
    		// 정보 삽입
    		try {
    			userDAO.insertUser(userDTO);
    			
    			UserResponseDto responseDto = new UserResponseDto(userDTO.getUser_id());
    			return ResponseEntity.ok(ApiResponse.success(responseDto));
    			
    		} catch (Exception e) {
    	         throw new RuntimeException(e);  // 글로벌 예외 처리로 500 에러 반환
    	    }
    		
    	} else {
    		// 가입 실패 : 아이디 중복
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("이미 존재하는 아이디입니다!"));
    	}

        
    }
  
    

}
