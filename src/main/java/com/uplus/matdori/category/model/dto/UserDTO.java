package com.uplus.matdori.category.model.dto;

//유저 관련 DTO
public class UserDTO {
    private String userId; // 회원 ID (VARCHAR 10)
    private int point; // 포인트 (INT)
    private String password; // 비밀번호 (VARCHAR 16)
    private int[] categoryVisits = new int[15]; // 카테고리별 방문 횟수 (C_1 ~ C_15)

    // 기본 생성자
    public UserDTO() {}

    // 모든 필드를 포함한 생성자
    public UserDTO(String userId, int point, String password, int[] categoryVisits) {
        this.userId = userId;
        this.point = point;
        this.password = password;
        this.categoryVisits = categoryVisits;
    }

    // Getter & Setter
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public int getPoint() { return point; }
    public void setPoint(int point) { this.point = point; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int[] getCategoryVisits() { return categoryVisits; }
    public void setCategoryVisits(int[] categoryVisits) { this.categoryVisits = categoryVisits; }

    // 특정 카테고리 방문 횟수 조회 & 변경
    public int getCategoryVisitCount(int categoryIndex) {
        return categoryVisits[categoryIndex - 1];
    }

    public void setCategoryVisitCount(int categoryIndex, int count) {
        categoryVisits[categoryIndex - 1] = count;
    }
}
