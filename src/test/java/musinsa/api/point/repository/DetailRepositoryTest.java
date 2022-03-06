package musinsa.api.point.repository;

import musinsa.api.point.entity.DetailEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@DataJpaTest
public class DetailRepositoryTest {

    @Autowired
    private DetailRepository detailRepository;

    @AfterEach
    public void cleanup() {
        detailRepository.deleteAll();
    }

    @Test
    public void 포인트상세데이터_넣기() {

        String type = "increase";
        int point = 1;
        long eventId = 1;
        long earnId = 1;
        String date = "2022-03-04";

        detailRepository.save(DetailEntity.builder()
                .type(type)
                .point(point)
                .eventId(eventId)
                .earnId(earnId)
                .date(date)
                .build());


        List<DetailEntity> detailList = detailRepository.findAll();

        DetailEntity detailEntity = detailList.get(0);

        assertThat(detailEntity.getType()).isEqualTo(type);
        assertThat(detailEntity.getDate()).isEqualTo(date);
        assertThat(detailEntity.getPoint()).isEqualTo(point);
        assertThat(detailEntity.getEventId()).isEqualTo(eventId);
        assertThat(detailEntity.getEarnId()).isEqualTo(earnId);





    }
}
