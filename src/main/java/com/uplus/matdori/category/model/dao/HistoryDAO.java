package com.uplus.matdori.category.model.dao;

import com.uplus.matdori.category.model.dto.HistoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface HistoryDAO {
	// 특정 회원의 히스토리 리스트 조회
	List<HistoryDTO> getUserHistory(@Param("user_id") String user_id);
    
	//방문한 식당 정보를 기록
    void insertVisitHistory(HistoryDTO visitHistory);
}
