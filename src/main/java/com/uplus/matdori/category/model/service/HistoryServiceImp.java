package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dao.HistoryDAO;
import com.uplus.matdori.category.model.dto.CategoryDTO;
import com.uplus.matdori.category.model.dto.HistoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

//History 관련 정보들을 처리하는 기능을 가진 HistoryServiceImp
@Service
public class HistoryServiceImp implements HistoryService {
    private final HistoryDAO historyDAO;

    public HistoryServiceImp(HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }

    @Override
    public List<HistoryDTO> getUserHistory(String userId) {
        return List.of();
    }

    @Override
    public void rateHistory(HistoryDTO historyDTO) {

    }

    @Override
    public void deleteHistory(int historyId) {

    }
}
