package musinsa.api.point.service;

import lombok.RequiredArgsConstructor;
import musinsa.api.point.entity.DetailEntity;
import musinsa.api.point.entity.EventEntity;
import musinsa.api.point.repository.DetailRepository;
import musinsa.api.point.repository.EventRepository;
import musinsa.api.point.request.PointRequest;
import musinsa.common.config.PointEnum;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PointService {
    private final DetailRepository detailRepository;
    private final EventRepository eventRepository;

    @Transactional
    public Long savePoint(PointRequest pointRequest) {

        if (!pointRequest.getType().equals(PointEnum.INCREASE.getValue())) {
            pointRequest.setPoint(pointRequest.getPoint()*-1);
        }
        //이벤트 테이블에 insert
        EventEntity eventEntity = pointRequest.toEventEntity();
        Long eventId = eventRepository.save(eventEntity).getId();

        // 상세내역 테이블에 insert
        pointRequest.setEventId(eventId);
        saveDetail(pointRequest);

        return eventId;
    }

    public void saveDetail(PointRequest pointRequest){

        //포인트 차감이라면 유효기간에 맞게 먼저 적립된 순서대로 사용되어야함.
        if(!pointRequest.getType().equals(PointEnum.INCREASE.getValue())){
            int point = pointRequest.getPoint();
            long owner = pointRequest.getOwner();

            detailRepository.findGroupByEarnId(owner);

        }
        DetailEntity detailEntity = pointRequest.toDetailEntity();
        Long id = detailRepository.save(detailEntity).getId();
        detailRepository.updateEarnId(id);

    }
}