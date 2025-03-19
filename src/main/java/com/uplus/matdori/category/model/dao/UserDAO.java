package com.uplus.matdori.category.model.dao;

import com.uplus.matdori.category.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDAO {

    // 특정 회원 정보 조회
    UserDTO getUserById(String userId);

    // 새로운 회원 정보 삽입
    void insertUser(UserDTO user);

    // 회원 정보 업데이트 (포인트 및 방문 카운트)
    void updateUser(UserDTO user);

    // 특정 카테고리 방문 횟수 조회
    int getCategoryVisitCount(@Param("user_id") String userId, @Param("columnName") String columnName);

    //특정 카테고리 방문 횟수 증가
    void incrementCategoryVisitCount(@Param("user_id") String userId, @Param("categoryId") int categoryId);
}