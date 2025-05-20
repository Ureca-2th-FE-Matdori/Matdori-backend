package com.uplus.matdori.category.model.dto;

import lombok.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

//lombok을 사용해서 Getter, Setter 바로 그냥 생성함
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private String user_id;  // 회원 ID (VARCHAR 10)
    private int point;  // 포인트 (INT)
    private String password;  // 비밀번호 (VARCHAR 16)
    private List<Integer> categoryVisits; // 카테고리별 방문 횟수 (C_1 ~ C_15)

    // 카테고리별 방문 횟수 조회
    public List<Integer> getCategoryVisits() {
        if (categoryVisits == null) {
            categoryVisits = new ArrayList<>(Collections.nCopies(15, 0));
        }
        return categoryVisits;
    }

    // 특정 카테고리 방문 횟수 조회 (매개변수로 카테고리 id를 넣으면 됨)
    public int getCategoryVisitCount(int categoryIndex) {
        if (categoryIndex < 1 || categoryIndex > 15) {
            throw new IndexOutOfBoundsException();
        }
        return categoryVisits.get(categoryIndex - 1); //categoryId는 1~15의 값을, 리스트는 0~14의 값을 사용한다
    }

    // 특정 카테고리 방문 횟수 증가
    public void incrementCategoryVisitCount(int categoryIndex) {
        if (categoryIndex < 1 || categoryIndex > 15) {
            throw new IndexOutOfBoundsException();
        }
        categoryVisits.set(categoryIndex - 1, categoryVisits.get(categoryIndex - 1) + 1);
    }

}