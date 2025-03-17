package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dao.UserDAO;
import com.uplus.matdori.category.model.dto.UserDTO;
import org.springframework.stereotype.Service;

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

    public void createAccount(UserDTO userDTO) {
        userDAO.insertUser(userDTO);
    }
}
