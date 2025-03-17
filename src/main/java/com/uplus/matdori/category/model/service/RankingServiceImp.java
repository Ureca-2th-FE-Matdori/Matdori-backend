package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dao.HistoryDAO;
import com.uplus.matdori.category.model.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

//랭킹 정보 관련 기능들을 포함한 RankingSericeImp
@Service
public class RankingServiceImp implements RankingService {
    private final HistoryDAO historyDAO;

    public RankingServiceImp(HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }

    @Override
    public List<CategoryDTO> getRankings() {
        return List.of();
    }
}
