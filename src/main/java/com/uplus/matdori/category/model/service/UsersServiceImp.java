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
    	
    	try {
    		//아이디 존재 여부 확인
    		UserDTO user = userDAO.getUserById(userId);
        
    		//아이디가 존재 하지 않고 비밀번호가 일치 하지 않는다면 에러 메세지
    		if(user == null || !user.getPassword().equals(password)) {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    					.body(ApiResponse.error("아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요."));
        
    		}else {	
    			// 아이디 비밀번호가 일치하면 로그인 성공
    			UserResponseDto responseDto = new UserResponseDto(user.getUser_id());
    			return ResponseEntity.ok(ApiResponse.success(responseDto));
        		}
    		// 글로벌 예외 처리로 500 에러 반환
    	}catch(Exception e) {
	         throw new RuntimeException(e);
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
