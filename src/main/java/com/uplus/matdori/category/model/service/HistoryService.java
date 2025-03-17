package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dto.HistoryDTO;

import java.util.List;

public interface HistoryService {
    List<HistoryDTO> getUserHistory(String userId);
    void rateHistory(HistoryDTO historyDTO);
    void deleteHistory(int historyId);
}
