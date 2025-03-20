package com.uplus.matdori.category.model.dao;

import com.uplus.matdori.category.model.dto.HistoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface HistoryDAO {

    //방문한 식당 정보를 기록
    void insertVisitHistory(HistoryDTO visitHistory);
}
