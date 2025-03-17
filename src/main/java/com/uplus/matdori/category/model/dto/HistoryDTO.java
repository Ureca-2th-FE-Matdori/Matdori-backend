package com.uplus.matdori.category.model.dto;

//히스토리 관련 DTO
public class HistoryDTO {
    private String userId; // 회원 ID (VARCHAR 10)
    private String url; // 식당 URL (TEXT)
    private int rate; // 평점 (INT)
    private int historyId; // 히스토리 ID (INT)
    private int categoryId; // 카테고리 ID (INT)

    // 기본 생성자
    public HistoryDTO() {}

    // 모든 필드를 포함한 생성자
    public HistoryDTO(String userId, String url, int rate, int historyId, int categoryId) {
        this.userId = userId;
        this.url = url;
        this.rate = rate;
        this.historyId = historyId;
        this.categoryId = categoryId;
    }

    // Getter & Setter
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public int getRate() { return rate; }
    public void setRate(int rate) { this.rate = rate; }

    public int getHistoryId() { return historyId; }
    public void setHistoryId(int historyId) { this.historyId = historyId; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
}
