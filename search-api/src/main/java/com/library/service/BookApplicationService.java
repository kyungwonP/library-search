package com.library.service;

import com.library.controller.response.PageResult;
import com.library.controller.response.SearchResponse;
import com.library.entity.DailyStat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookApplicationService {
    private final BookQueryService bookQueryService;
    private final DailyStatCommandService dailyStatCommandService;
    private final ApplicationEventPublisher eventPublisher;

    public PageResult<SearchResponse> search(String query, int page, int size) {
        // 외부 api 호출 -> 통계데이터 저장 -> api 호출 값 응답
        PageResult<SearchResponse> response = bookQueryService.search(query, page, size);
        DailyStat dailyStat = new DailyStat(query, LocalDateTime.now());
        dailyStatCommandService.save(dailyStat);
//        if (!response.contents().isEmpty()) {
//            log.info("검색결과 개수: {}", response.size());
//            eventPublisher.publishEvent(new SearchEvent(query, LocalDateTime.now()));
//        }
        return response;
    }

//    public StatResponse findQueryCount(String query, LocalDate date) {
//        return dailyStatQueryService.findQueryCount(query, date);
//    }
//
//    public List<StatResponse> findTop5Query() {
//        return dailyStatQueryService.findTop5Query();
//    }
}
