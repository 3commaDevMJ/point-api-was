package musinsa.api.point.repository;

import musinsa.api.point.entity.DetailEntity;
import musinsa.api.point.entity.EventEntity;
import musinsa.api.point.response.DetailGroupByResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<DetailEntity, Long> {
    List<DetailEntity> findAllByEventId(Long eventId);
    void deleteByEventId(Long eventId);
    @Modifying
    @Query(value = "update POINT_DETAIL set EARN_ID = :id where id = :id", nativeQuery = true)
    void updateEarnId(@Param(value = "id") Long id);

    @Query(value = "select EARN_ID as earnId, sum(POINT)as point FROM POINT_DETAIL where owner = :owner group by EARN_ID  HAVING sum(point) >0", nativeQuery = true)
    List<DetailGroupByResponse> findGroupByEarnId(Long owner);
}
