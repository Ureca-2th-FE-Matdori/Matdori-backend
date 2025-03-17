package com.uplus.matdori.category.controller;

import com.uplus.matdori.category.model.dto.UserDTO;
import com.uplus.matdori.category.model.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/*
 * @RestController
 *   RestFul Service를 위한 Conrtoller
 *   모든 메서드의 응답을  @ResponseBody를 붙여주는 효과
 */
@RestController

//RestFul에서 서비스할 자원(Domain)명을 붙인다
@RequestMapping("/users")

/*
 * @CrossOrigin
 *  - CORS 요청에 대한 승인
 *  origins = {"*"}  : 요청하는 모든 URL, 메서드를 승인
 *     ==> 보안에 취약하므로 상용에서는 사용 안함
 *     ==> 이후에 Configuration을 통해 설정할 예정
 * */
@CrossOrigin(origins = {"*"})


//회원 정보 관련 컨트롤러
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        boolean success = usersService.login(userDTO.getUserId(), userDTO.getPassword());
        return success ? ResponseEntity.ok("Login Success") : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
    }

    //회원가입(POST)
    @PostMapping("/createAccount")
    public ResponseEntity<String> createAccount(@RequestBody UserDTO userDTO) {
        usersService.createAccount(userDTO);
        return ResponseEntity.ok("Account Created Successfully");
    }
}
