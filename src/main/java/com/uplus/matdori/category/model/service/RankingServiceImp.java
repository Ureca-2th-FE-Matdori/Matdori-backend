package com.uplus.matdori.category.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uplus.matdori.category.model.dao.UserDAO;
import com.uplus.matdori.category.model.dto.UserDTO;
import com.uplus.matdori.category.model.dto.UserRankingDTO;

//랭킹 정보 관련 기능들을 포함한 RankingSericeImp
@Service
public class RankingServiceImp implements RankingService {
    private final UserDAO userDAO;

    public RankingServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<UserRankingDTO> getTopRankingUsers() {    	
        List<UserDTO> users = userDAO.getTopRankingUsers();
        System.out.println("랭킹 조회 결과: " + users);
        // UserDTO -> UserRankingDTO 변환 과정
        return users.stream()
        		.map(user -> new UserRankingDTO(user.getUser_id(), user.getPoint()))
        		.collect(Collectors.toList());
    }
}
