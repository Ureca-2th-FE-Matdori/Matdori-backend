package com.uplus.matdori.category.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uplus.matdori.category.model.dao.UserDAO;
import com.uplus.matdori.category.model.dto.ApiResponse;
import com.uplus.matdori.category.model.dto.MyRankingDTO;
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

	@Override
	public ResponseEntity<ApiResponse<MyRankingDTO>> getMyRanking(String user_id) {
		try {
			MyRankingDTO myRanking = userDAO.getMyRanking(user_id);
			// 존재하지 않는 회원의 랭킹을 조회한 경우
			if(myRanking == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error("존재하지 않는 회원입니다."));
			}
			// 존재하는 회원의 랭킹을 조회한 경우
			return ResponseEntity.ok(ApiResponse.success(myRanking));
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
