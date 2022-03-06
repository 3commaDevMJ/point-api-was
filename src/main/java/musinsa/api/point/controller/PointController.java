package musinsa.api.point.controller;

import lombok.RequiredArgsConstructor;
import musinsa.api.point.request.PointRequest;
import musinsa.api.point.response.PointResponse;
import musinsa.api.point.service.PointService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PointController {

    private final PointService pointService;

    // 포인트 적립 및 사용
    @PostMapping("/api/v1/point")
    public ResponseEntity<Long> savePoint(@RequestBody PointRequest pointRequest){
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok()
                .headers(headers)
                .body(pointService.savePoint(pointRequest));
    }


}
