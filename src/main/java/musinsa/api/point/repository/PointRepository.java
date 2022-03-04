package musinsa.api.point.repository;

import musinsa.api.point.entity.DetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<DetailEntity, String> {
}
