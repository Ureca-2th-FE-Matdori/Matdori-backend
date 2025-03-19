package com.uplus.matdori.category.model.dao;

import com.uplus.matdori.category.model.dto.HistoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface HistoryDAO {

    // 특정 사용자의 방문 히스토리 조회
    List<HistoryDTO> getHistoryByUserId(@Param("user_id") String userId);

    // 새로운 방문 히스토리 추가
    void insertHistory(HistoryDTO history);

    // 평점 업데이트
    void updateRating(HistoryDTO history);

    // 방문 히스토리 삭제
    void deleteHistory(@Param("historyId") int historyId);
}
