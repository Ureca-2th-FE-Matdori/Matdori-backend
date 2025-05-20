package com.uplus.matdori.category.model.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis의 TypeHandler를 사용하여 데이터베이스의 c_1 ~ c_15 컬럼 값을
 * Java의 List<Integer> 타입으로 변환하는 핸들러
 */
public class CategoryVisitsTypeHandler extends BaseTypeHandler<List<Integer>> {

    /**
     * PreparedStatement에 List<Integer> 값을 설정할 때 사용됨 (INSERT 또는 UPDATE 시)
     * @param ps PreparedStatement 객체
     * @param i 컬럼 인덱스 (이 TypeHandler를 사용하는 필드의 순서)
     * @param parameter categoryVisits 리스트 (DB에 저장될 데이터)
     * @param jdbcType JDBC 데이터 타입
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Integer> parameter, JdbcType jdbcType) throws SQLException {
        for (int index = 0; index < parameter.size(); index++) {
            ps.setInt(i + index + 1, parameter.get(index)); // 1-based index
        }
    }

    /**
     * ResultSet에서 단일 컬럼 값을 읽어 List<Integer>로 변환하는 메서드
     * 그러나, 이 핸들러에서는 단일 컬럼이 아닌 여러 컬럼(c_1 ~ c_15)을 사용하므로 실제로는 사용되지 않음.
     */
    @Override
    public List<Integer> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getCategoryVisitsFromResultSet(rs);
    }

    /**
     * ResultSet에서 컬럼 인덱스를 기반으로 값을 읽어 List<Integer>로 변환하는 메서드
     * 그러나, 이 핸들러에서는 컬럼 이름을 기반으로 조회하므로 사용되지 않음.
     */
    @Override
    public List<Integer> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getCategoryVisitsFromResultSet(rs);
    }

    /**
     * CallableStatement에서 컬럼 인덱스를 기반으로 값을 읽는 메서드 (Stored Procedure 사용 시)
     * 현재 프로젝트에서는 사용되지 않음.
     */
    @Override
    public List<Integer> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }

    /**
     * ResultSet에서 c_1 ~ c_15 컬럼 값을 읽어 List<Integer>로 변환하는 메서드
     * @param rs MyBatis에서 반환된 ResultSet 객체
     * @return c_1 ~ c_15 값을 담은 List<Integer>
     */
    private List<Integer> getCategoryVisitsFromResultSet(ResultSet rs) throws SQLException {
        List<Integer> categoryVisits = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            categoryVisits.add(rs.getInt("c_" + i));
        }
        return categoryVisits;
    }
}
