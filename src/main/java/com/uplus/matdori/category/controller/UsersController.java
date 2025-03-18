package com.uplus.matdori.category.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uplus.matdori.category.model.dto.ApiResponse;
import com.uplus.matdori.category.model.dto.UserDTO;
import com.uplus.matdori.category.model.dto.UserResponseDto;
import com.uplus.matdori.category.model.service.UsersService;


@RestController

@RequestMapping("/users")

@CrossOrigin(origins = {"*"})


//회원 정보 관련 컨트롤러
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        boolean success = usersService.login(userDTO.getUser_id(), userDTO.getPassword());
        return success ? ResponseEntity.ok("Login Success") : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
    }

    //회원가입(POST)
    @PostMapping("/createAccount")
    public ResponseEntity<ApiResponse<UserResponseDto>> createAccount(@RequestBody UserDTO userDTO) {
        return usersService.createAccount(userDTO);
    }
   
}
