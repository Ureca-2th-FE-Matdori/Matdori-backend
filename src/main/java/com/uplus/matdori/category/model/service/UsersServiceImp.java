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
 
    
    //로그인
    public ResponseEntity<ApiResponse<UserResponseDto>> login(String userId, String password) {
    	
    	//아이디 존재 여부 확인
        UserDTO user = userDAO.getUserById(userId);
        
        //아이디가 존재 하지 않는 다면 존재하지 않는 계정 반환
        if(user == null) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("존재하지 않는 계정입니다. 회원가입을 진행해 주세요"));
        }
        
        //아이디가 존재 하고 비밀번호가 일치 한다면 로그인 성공
        if(user.getPassword().equals(password)) {
        	UserResponseDto responseDto = new UserResponseDto(user.getUser_id());
			return ResponseEntity.ok(ApiResponse.success(responseDto));
			
        }else {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("비밀번호가 일치하지 않습니다."));
        }
    }
   
    // 회원 가입 진행 시 아이디 중복 확인 (같은 아이디가 있다면 회원가입 false)
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
    		// id가 null 값이 맞다면 가입 처리 진행
    	if(checkUserId(userDTO.getUser_id())) {
    		// 정보 삽입
    		try {
    			userDAO.insertUser(userDTO);
    			
    			UserResponseDto responseDto = new UserResponseDto(userDTO.getUser_id());
    			return ResponseEntity.ok(ApiResponse.success(responseDto));
    			
    		} catch (Exception e) {
    	         throw new RuntimeException(e);  // 글로벌 예외 처리로 500 에러 반환
    	    }
    		
    	} else {
    		// 가입 실패 : 아이디 중복 (상태 코드 및 에러 메세지 전달)
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("이미 존재하는 아이디입니다!"));
    	}    
    }
}
