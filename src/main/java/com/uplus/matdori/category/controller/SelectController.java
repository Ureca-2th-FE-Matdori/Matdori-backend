package com.uplus.matdori.category.controller;

import com.uplus.matdori.category.model.dao.CategoryDAO;
import com.uplus.matdori.category.model.dao.UserDAO;
import com.uplus.matdori.category.model.dto.CategoryDTO;
import com.uplus.matdori.category.model.dto.UserDTO;
import com.uplus.matdori.category.model.service.SelectService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/*
 * @RestController
 *   RestFul Service를 위한 Conrtoller
 *   모든 메서드의 응답을  @ResponseBody를 붙여주는 효과
 */
@RestController

//RestFul에서 서비스할 자원(Domain)명을 붙인다
@RequestMapping("/select")

/*
 * @CrossOrigin
 *  - CORS 요청에 대한 승인
 *  origins = {"*"}  : 요청하는 모든 URL, 메서드를 승인
 *     ==> 보안에 취약하므로 상용에서는 사용 안함
 *     ==> 이후에 Configuration을 통해 설정할 예정
 * */
@CrossOrigin(origins = {"*"})

//뽑기 관련 컨트롤러
public class SelectController implements SelectService {
    private final CategoryDAO categoryDAO;
    private final UserDAO userDAO;
    private final Random random = new Random();

    public SelectController(CategoryDAO categoryDAO, UserDAO userDAO) {
        this.categoryDAO = categoryDAO;
        this.userDAO = userDAO;
    }

    @Override
    public CategoryDTO getRandomCategory() {
        int randomId = (random.nextInt(15) + 1);
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
