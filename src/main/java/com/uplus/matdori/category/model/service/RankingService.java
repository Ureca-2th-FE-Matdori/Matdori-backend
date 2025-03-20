package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dto.UserRankingDTO;
import com.uplus.matdori.category.model.dto.ApiResponse;
import com.uplus.matdori.category.model.dto.MyRankingDTO;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface RankingService {
    List<UserRankingDTO> getTopRankingUsers();
    ResponseEntity<ApiResponse<MyRankingDTO>> getMyRanking(String user_id);
}
