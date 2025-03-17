package com.uplus.matdori.category.model.dao;

import com.uplus.matdori.category.model.dto.UserDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDAO {
    @Select("SELECT * FROM 회원정보 WHERE user_id = #{userId}")
    UserDTO getUserById(@Param("userId") String userId);

    @Insert("INSERT INTO 회원정보 (user_id, point, password, c_1, c_2, c_3, c_4, c_5, c_6, c_7, c_8, c_9, c_10, c_11, c_12, c_13, c_14, c_15) " +
            "VALUES (#{userId}, #{point}, #{password}, " +
            "#{categoryVisits[0]}, #{categoryVisits[1]}, #{categoryVisits[2]}, #{categoryVisits[3]}, #{categoryVisits[4]}, " +
            "#{categoryVisits[5]}, #{categoryVisits[6]}, #{categoryVisits[7]}, #{categoryVisits[8]}, #{categoryVisits[9]}, " +
            "#{categoryVisits[10]}, #{categoryVisits[11]}, #{categoryVisits[12]}, #{categoryVisits[13]}, #{categoryVisits[14]})")
    void insertUser(UserDTO userDTO);

    @Update("UPDATE 회원정보 SET point = #{point}, password = #{password}, " +
            "c_1 = #{categoryVisits[0]}, c_2 = #{categoryVisits[1]}, c_3 = #{categoryVisits[2]}, " +
            "c_4 = #{categoryVisits[3]}, c_5 = #{categoryVisits[4]}, c_6 = #{categoryVisits[5]}, " +
            "c_7 = #{categoryVisits[6]}, c_8 = #{categoryVisits[7]}, c_9 = #{categoryVisits[8]}, " +
            "c_10 = #{categoryVisits[9]}, c_11 = #{categoryVisits[10]}, c_12 = #{categoryVisits[11]}, " +
            "c_13 = #{categoryVisits[12]}, c_14 = #{categoryVisits[13]}, c_15 = #{categoryVisits[14]} " +
            "WHERE user_id = #{userId}")
    void updateUser(UserDTO userDTO);
}
