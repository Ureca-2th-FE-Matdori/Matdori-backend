package com.uplus.matdori.category.controller;

import com.uplus.matdori.category.model.dto.HistoryDTO;
import com.uplus.matdori.category.model.service.HistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @RestController
 *   RestFul Service를 위한 Conrtoller
 *   모든 메서드의 응답을  @ResponseBody를 붙여주는 효과
 */
@RestController

//RestFul에서 서비스할 자원(Domain)명을 붙인다
@RequestMapping("/history")

/*
 * @CrossOrigin
 *  - CORS 요청에 대한 승인
 *  origins = {"*"}  : 요청하는 모든 URL, 메서드를 승인
 *     ==> 보안에 취약하므로 상용에서는 사용 안함
 *     ==> 이후에 Configuration을 통해 설정할 예정
 * */
@CrossOrigin(origins = {"*"})
//히스토리 관련 컨트롤러
public class HistoryController {
    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/getTable/{userId}")
    public List<HistoryDTO> getUserHistory(@PathVariable String userId) {
        return historyService.getUserHistory(userId);
    }

    @PostMapping("/rate")
    public ResponseEntity<String> rateHistory(@RequestBody HistoryDTO historyDTO) {
        historyService.rateHistory(historyDTO);
        return ResponseEntity.ok("Rating Submitted Successfully");
    }

    @DeleteMapping("/delete/{historyId}")
    public ResponseEntity<String> deleteHistory(@PathVariable int historyId) {
        historyService.deleteHistory(historyId);
        return ResponseEntity.ok("History Deleted Successfully");
    }
}
