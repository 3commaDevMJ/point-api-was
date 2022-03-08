package musinsa.api.point.controller;

import lombok.RequiredArgsConstructor;
import musinsa.api.point.request.PointRequest;
import musinsa.api.point.response.PointResponse;
import musinsa.api.point.response.PointSumResponse;
import musinsa.api.point.service.PointService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PointController {

    private final PointService pointService;

    // 포인트 적립 및 사용
    @PostMapping("/api/v1/point")
    public ResponseEntity<Long> savePoint(@Valid @RequestBody PointRequest pointRequest){
        return ResponseEntity.ok()
                .body(pointService.savePoint(pointRequest));
    }

    // 포인트 내역조회
    @GetMapping("/api/v1/owner/{owner}/points")
    public ResponseEntity<List<PointResponse>> getPointList(@PathVariable("owner") long owner, Pageable pageable){
        return ResponseEntity.ok()
                .body(pointService.getPointList(owner,pageable));
    }
    // 포인트 합산조회
    @GetMapping("/api/v1/owner/{owner}/point/amount")
    public ResponseEntity<PointSumResponse> getPoint(@PathVariable("owner") long owner){
        return ResponseEntity.ok()
                .body(pointService.getPoint(owner));
    }
    // 포인트 사용 취소
    @DeleteMapping("/api/v1/point/{eventId}")
    public ResponseEntity<Boolean> cancelPoint(@PathVariable("eventId") long eventId){
        return ResponseEntity.ok()
                .body(pointService.cancelPoint(eventId));
    }

}
