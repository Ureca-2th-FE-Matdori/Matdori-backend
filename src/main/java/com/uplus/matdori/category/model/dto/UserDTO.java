package com.uplus.matdori.category.model.dto;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class UserDTO {
    private String userId;  // 회원 ID (VARCHAR 10)
    private int point;  // 포인트 (INT)
    private String password;  // 비밀번호 (VARCHAR 16)
    private List<Integer> categoryVisits;  // 카테고리별 방문 횟수 (C_1 ~ C_15)

    // 기본 생성자
    public UserDTO() {
        // 초기값을 0으로 설정된 리스트 생성 (카테고리 15개)
        this.categoryVisits = new ArrayList<>(Collections.nCopies(15, 0));
    }

    // 모든 필드를 포함한 생성자
    public UserDTO(String userId, int point, String password, List<Integer> categoryVisits) {
        this.userId = userId;
        this.point = point;
        this.password = password;
        this.categoryVisits = categoryVisits != null ? categoryVisits : new ArrayList<>(Collections.nCopies(15, 0));
    }

    // Getter & Setter
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public int getPoint() { return point; }
    public void setPoint(int point) { this.point = point; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<Integer> getCategoryVisits() { return categoryVisits; }
    public void setCategoryVisits(List<Integer> categoryVisits) {
        this.categoryVisits = categoryVisits != null ? categoryVisits : new ArrayList<>(Collections.nCopies(15, 0));
    }

    // 특정 카테고리 방문 횟수 조회
    public int getCategoryVisitCount(int categoryIndex) {
        if (categoryIndex < 1 || categoryIndex > 15) {
            throw new IndexOutOfBoundsException("Category index must be between 1 and 15.");
        }
        return categoryVisits.get(categoryIndex - 1);
    }

    // 특정 카테고리 방문 횟수 변경
    public void setCategoryVisitCount(int categoryIndex, int count) {
        if (categoryIndex < 1 || categoryIndex > 15) {
            throw new IndexOutOfBoundsException("Category index must be between 1 and 15.");
        }
        categoryVisits.set(categoryIndex - 1, count);
    }
}

