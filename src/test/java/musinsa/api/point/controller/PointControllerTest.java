package musinsa.api.point.controller;

import musinsa.api.point.entity.EventEntity;
import musinsa.api.point.repository.EventRepository;
import musinsa.api.point.request.PointRequest;
import musinsa.api.point.response.PointResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PointControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private EventRepository eventRepository;

    @AfterEach
    public void down() throws Exception{
        eventRepository.deleteAll();
    }

    public void insertData() throws Exception{
        long owner = 1;
        String type = "increase";
        int point = 100;

        PointRequest pointRequest = PointRequest.builder()
                .point(point)
                .owner(owner)
                .type(type)
                .build();

        String url = "http://localhost:" +port + "/api/v1/point";

        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url,pointRequest,Long.class);
    }

    @Test
    public void 내역조회테스트() throws Exception{
        for(int i =0; i<10; i++){
            insertData();
        }
        Pageable pageable = PageRequest.of(0, 3);
        List<EventEntity> points = eventRepository.findAllByOwner(1L, pageable);
        points.forEach(eventEntity ->
                System.out.println(eventEntity.toString())
        );
    }

    @Test
    public void 이벤트등록된다() throws Exception{

        long owner = 1;
        String type = "increase";
        int point = 100;

        PointRequest pointRequest = PointRequest.builder()
                .point(point)
                .owner(owner)
                .type(type)
                .build();

        String url = "http://localhost:" +port + "/api/v1/point";

        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url,pointRequest,Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        //첫번째값이 내가 원하는 값인지
        List<EventEntity> all = eventRepository.findAll();
        assertThat(all.get(0).getType()).isEqualTo(type);
        assertThat(all.get(0).getOwner()).isEqualTo(owner);
        assertThat(all.get(0).getPoint()).isEqualTo(point);

        //삭제까지 이뤄지는지...
        System.out.printf(responseEntity.getBody()+"\n");
        url = "http://localhost:" +port + "/api/v1/point/"+responseEntity.getBody();
       testRestTemplate.delete(url);
    }

}
