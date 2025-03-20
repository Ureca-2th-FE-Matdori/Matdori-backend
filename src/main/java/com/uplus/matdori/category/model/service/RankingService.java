package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dto.UserRankingDTO;

import java.util.List;

public interface RankingService {
    List<UserRankingDTO> getTopRankingUsers();
}
