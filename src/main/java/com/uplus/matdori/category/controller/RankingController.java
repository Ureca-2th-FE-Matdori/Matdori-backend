package com.uplus.matdori.category.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uplus.matdori.category.model.dto.ApiResponse;
import com.uplus.matdori.category.model.dto.MyRankingDTO;
import com.uplus.matdori.category.model.dto.UserRankingDTO;
import com.uplus.matdori.category.model.service.RankingService;

/*
 * @RestController
 *   RestFul Service를 위한 Conrtoller
 *   모든 메서드의 응답을  @ResponseBody를 붙여주는 효과
 */
@RestController

//RestFul에서 서비스할 자원(Domain)명을 붙인다
@RequestMapping("/ranking")

/*
 * @CrossOrigin
 *  - CORS 요청에 대한 승인
 *  origins = {"*"}  : 요청하는 모든 URL, 메서드를 승인
 *     ==> 보안에 취약하므로 상용에서는 사용 안함
 *     ==> 이후에 Configuration을 통해 설정할 예정
 * */
@CrossOrigin(origins = {"*"})

//랭킹 관련 컨트롤러
public class RankingController {
    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/top10")
    public ResponseEntity<ApiResponse<List<UserRankingDTO>>> getTopRankingUsers() {
        List<UserRankingDTO> topUsers = rankingService.getTopRankingUsers();
        return ResponseEntity.ok(ApiResponse.success(topUsers));
    }
}
