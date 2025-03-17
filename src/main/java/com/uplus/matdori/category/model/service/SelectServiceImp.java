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
    public CategoryDTO getRandomCategory() {
        int randomId = random.nextInt(15) + 1;
        return categoryDAO.getCategoryById(randomId);
    }

    @Override
    public CategoryDTO getPreferredCategory(String userId) {
        UserDTO user = userDAO.getUserById(userId);
        int maxVisits = 0, bestCategory = 1;
        for (int i = 0; i < user.getCategoryVisits().length; i++) {
            if (user.getCategoryVisits()[i] > maxVisits) {
                maxVisits = user.getCategoryVisits()[i];
                bestCategory = i + 1;
            }
        }
        return categoryDAO.getCategoryById(bestCategory);
    }

    @Override
    public CategoryDTO getCompletelyRandomSelection() {
        List<CategoryDTO> categories = categoryDAO.getAllCategories();
        return categories.get(random.nextInt(categories.size()));
    }
}
