package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dao.HistoryDAO;
import com.uplus.matdori.category.model.dto.HistoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uplus.matdori.category.model.dao.UserDAO;
import com.uplus.matdori.category.model.dto.ApiResponse;
import com.uplus.matdori.category.model.dto.UserDTO;
import com.uplus.matdori.category.model.dto.UserResponseDto;
import org.springframework.transaction.annotation.Transactional;

//회원 정보 관련 기능들을 구현한 UsersServiceImp
@Slf4j
@Service
public class UsersServiceImp implements UsersService {
    private final UserDAO userDAO;
	LoggerFactory loggerFactory;

	@Autowired
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
    	//1. 아이디 중복 검사 진행 -> 가입 실패 : 아이디 중복 (상태 코드 및 에러 메세지 전달)
    	if(!checkUserId(userDTO.getUser_id())) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                  .body(ApiResponse.error("이미 존재하는 아이디입니다!"));	
    	}
    	//2. 아이디 길이 5 ~ 10자로 제한 ->가입 실패 : 아이디 길이제한 (상태 코드 및 에러 메세지 전달)
    	if(userDTO.getUser_id().length() < 5 || userDTO.getUser_id().length()>10) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("아이디는 5자 이상 10자 이하로 입력해주세요."));	
    	}
    	//3. 비밀번호 길이 5 ~ 16자로 제한 ->가입 실패 : 비밀번호 길이제한 (상태 코드 및 에러 메세지 전달)
    	if(userDTO.getPassword().length() < 5 || userDTO.getPassword().length()>16) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("비밀번호는 5자 이상 16자 이하로 입력해주세요."));	
    	}
    	//4. 3가지 정보 통과하는 경우 회원 정보 삽입
    	try {
    		// 회원 정보 삽입
    		userDAO.insertUser(userDTO);
    		
    		UserResponseDto responseDto = new UserResponseDto(userDTO.getUser_id());
			return ResponseEntity.ok(ApiResponse.success(responseDto));	
			//삽입이 안됐을 때 글로벌 예외 처리로 500 에러 반환
    	}catch(Exception e) {
	         throw new RuntimeException(e);
	    }
    }
}
