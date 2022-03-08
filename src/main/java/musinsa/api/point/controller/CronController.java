package musinsa.api.point.controller;

import lombok.RequiredArgsConstructor;
import musinsa.api.point.service.PointService;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@EnableAsync
public class CronController {
    private final PointService pointService;

    //포인트 유효기간(배치프로그램은 별도분리필요)
    @Scheduled(cron = "0 0 * */1 * *")
    public void expirePoint() {
        pointService.expirePoint();
    }
}
