package musinsa.api.point.repository;

import musinsa.api.point.entity.EventEntity;
import musinsa.api.point.response.DetailGroupByResponse;
import musinsa.api.point.response.ExpireRespone;
import musinsa.api.point.response.PointResponse;
import musinsa.api.point.response.PointSumResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity,Long> {

    List<EventEntity> findAllByOwner(Long owner, Pageable pageable);

    @Query(value = "select sum(POINT)as point FROM POINT_EVENT where owner = :owner", nativeQuery = true)
    public PointSumResponse findGroupByOwner(Long owner);

    @Query(value = "select detail.OWNER as owner ,detail.EARN_ID as earnId,event.expire_date as expireDate, sum(detail.POINT)as point FROM POINT_DETAIL as detail inner join POINT_EVENT as event on detail.EVENT_ID = event.ID where event.expire_date < :expireDate group by EARN_ID  HAVING sum(detail.point) >0", nativeQuery = true)
    public List<ExpireRespone> findExpire(String expireDate);

}
