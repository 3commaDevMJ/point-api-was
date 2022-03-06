package musinsa.api.point.controller;

import musinsa.api.point.entity.EventEntity;
import musinsa.api.point.repository.EventRepository;
import musinsa.api.point.request.PointRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
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

        List<EventEntity> all = eventRepository.findAll();
        assertThat(all.get(0).getType()).isEqualTo(type);
    }

}
