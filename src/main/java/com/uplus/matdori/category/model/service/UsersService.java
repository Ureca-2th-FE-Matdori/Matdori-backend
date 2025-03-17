package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dao.UserDAO;
import com.uplus.matdori.category.model.dto.UserDTO;
import org.springframework.stereotype.Service;

//회원정보 관련 비즈니스 로직 처리 (interface)
@Service
public interface UsersService {
    boolean login(String userId, String password);
    void createAccount(UserDTO userDTO);
}
