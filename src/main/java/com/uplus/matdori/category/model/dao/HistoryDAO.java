package com.uplus.matdori.category.model.dao;

import com.uplus.matdori.category.model.dto.CategoryDTO;
import com.uplus.matdori.category.model.dto.HistoryDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HistoryDAO {
    @Select("SELECT * FROM 방문한_식당_히스토리 WHERE user_id2 = #{userId}")
    List<HistoryDTO> getHistoryByUserId(@Param("userId") String userId);

    @Insert("INSERT INTO 방문한_식당_히스토리 (user_id2, url, rate, history_id, category_id2) " +
            "VALUES (#{userId}, #{url}, #{rate}, #{historyId}, #{categoryId})")
    void insertHistory(HistoryDTO history);

    @Update("UPDATE 방문한_식당_히스토리 SET rate = #{rate} WHERE history_id = #{historyId}")
    void updateRating(HistoryDTO history);

    @Delete("DELETE FROM 방문한_식당_히스토리 WHERE history_id = #{historyId}")
    void deleteHistory(@Param("historyId") int historyId);

    List<CategoryDTO> getCategoryRanking();
}
