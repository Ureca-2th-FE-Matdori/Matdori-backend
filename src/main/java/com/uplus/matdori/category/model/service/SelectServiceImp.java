package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dao.CategoryDAO;
import com.uplus.matdori.category.model.dao.UserDAO;
import com.uplus.matdori.category.model.dto.CategoryDTO;
import com.uplus.matdori.category.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

//뽑기 관련 Service 구현
@Service
public class SelectServiceImp implements SelectService {
    private final CategoryDAO categoryDAO;
    private final UserDAO userDAO;
    private final Random random = new Random();

    public SelectServiceImp(CategoryDAO categoryDAO, UserDAO userDAO) {
        this.categoryDAO = categoryDAO;
        this.userDAO = userDAO;
    }

    @Override
    public String getRandomCategory() {
        int randomId = random.nextInt(15) + 1; // 1~15 랜덤 숫자 생성
        return categoryDAO.search(randomId);
    }

    @Override
    public String getPreferredCategory(String userId) {
        UserDTO user = userDAO.getUserById(userId);
        List<Integer> categoryVisits = user.getCategoryVisits(); // List<Integer>로 변경

        int maxVisits = 0, bestCategory = 1;
        for (int i = 0; i < categoryVisits.size(); i++) { // .length → .size() 사용
            if (categoryVisits.get(i) > maxVisits) { // 배열 인덱싱 → get(i) 사용
                maxVisits = categoryVisits.get(i);
                bestCategory = i + 1;
            }
        }
        return categoryDAO.search(bestCategory);
    }
}
