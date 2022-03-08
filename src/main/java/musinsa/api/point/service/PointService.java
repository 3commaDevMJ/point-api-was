package musinsa.api.point.service;

import lombok.RequiredArgsConstructor;
import musinsa.api.point.entity.DetailEntity;
import musinsa.api.point.entity.EventEntity;
import musinsa.api.point.exception.PointException;
import musinsa.api.point.repository.DetailRepository;
import musinsa.api.point.repository.EventRepository;
import musinsa.api.point.request.PointRequest;
import musinsa.api.point.response.DetailGroupByResponse;
import musinsa.api.point.response.ExpireRespone;
import musinsa.api.point.response.PointResponse;
import musinsa.api.point.response.PointSumResponse;
import musinsa.common.config.PointEnum;
import musinsa.common.config.StatusEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PointService {
    private final DetailRepository detailRepository;
    private final EventRepository eventRepository;

    //포인트 사용 취소
    @Transactional
    public boolean cancelPoint(long eventId) {
        EventEntity eventEntity = EventEntity.builder()
                .id(eventId)
                .build();

        eventRepository.deleteById(eventId);
        DetailEntity detailEntity = DetailEntity.builder()
                .eventId(eventId)
                .build();
        detailRepository.deleteByEventId(eventId);

        return true;
    }

    // 포인트 유효기간처리.
    public void expirePoint() {
        LocalDateTime localDateTime = LocalDateTime.now().plusYears(1).minusSeconds(1);

        String expireTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<ExpireRespone> expireList = eventRepository.findExpire(expireTime);
        for (ExpireRespone expireRespone : expireList) {
            PointRequest expireRequest = PointRequest.builder()
                    .owner(expireRespone.getOwner())
                    .expireDate(expireRespone.getExpireDate())
                    .type(PointEnum.EXPIRE.getValue())
                    .date(LocalDate.now().toString())
                    .point(expireRespone.getPoint())
                    .build();
            savePoint(expireRequest);
        }
    }

    // 포인트 내역 조회.
    public List<PointResponse> getPointList(long owner, Pageable pageable) {

        PointResponse pointResponse = new PointResponse();
        return pointResponse.toResponseList(eventRepository.findAllByOwner(owner, pageable));
    }

    public PointSumResponse getPoint(long owner) {
        return eventRepository.findGroupByOwner(owner);
    }

    @Transactional
    public Long savePoint(PointRequest pointRequest) {

        if (!pointRequest.getType().equals(PointEnum.INCREASE.getValue())) {
            pointRequest.setPoint(pointRequest.getPoint() * -1);
        }
        //이벤트 테이블에 insert
        EventEntity eventEntity = pointRequest.toEventEntity();
        Long eventId = eventRepository.save(eventEntity).getId();

        // 상세내역 테이블에 insert
        pointRequest.setEventId(eventId);
        saveDetail(pointRequest);

        return eventId;
    }

    public void saveDetail(PointRequest pointRequest) {
        //포인트 차감이라면 유효기간에 맞게 먼저 적립된 순서대로 사용되어야함.
        if (pointRequest.getType().equals(PointEnum.INCREASE.getValue())) {
            Long id = detailRepository.save(pointRequest.toDetailEntity()).getId();
            detailRepository.updateEarnId(id);
        } else {
            int point = pointRequest.getPoint();
            long owner = pointRequest.getOwner();
            List<DetailGroupByResponse> detailList = detailRepository.findGroupByEarnId(owner);
            if (detailList.isEmpty()) throw new PointException(StatusEnum.NO_POINT);
            for (DetailGroupByResponse detail : detailList) {
                // 현재 항목의 포인트가 빼야할것보다 크다면
                if (detail.getPoint() >= Math.abs(point)) {
                    if (point == 0) break;
                    pointRequest.setEarnId(detail.getEarnId());
                    pointRequest.setPoint(point);
                    detailRepository.save(pointRequest.toDetailEntity());
                    point = 0;
                } else {
                    point = detail.getPoint() - Math.abs(point);
                    pointRequest.setEarnId(detail.getEarnId());
                    pointRequest.setPoint(detail.getPoint() * -1);
                    detailRepository.save(pointRequest.toDetailEntity());
                }

            }
        }


    }
}
