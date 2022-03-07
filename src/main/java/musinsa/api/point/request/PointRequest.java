package musinsa.api.point.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import musinsa.api.point.entity.DetailEntity;
import musinsa.api.point.entity.EventEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PointRequest {
    private Long owner;
    private int point;
    private String type;
    private String date = LocalDate.now().toString();
    private String expireDate = LocalDateTime.now().plusYears(1).toString();

    //detail
    private Long earnId;
    private Long eventId;

    @Builder
    public PointRequest (Long owner, int point, String type, String date, String expireDate){
        this.owner = owner;
        this.point = point;
        this.type = type;
        this.date = date;
        this.expireDate = expireDate;
    }

    public EventEntity toEventEntity(){
        return EventEntity.builder()
                .owner(owner)
                .point(point)
                .type(type)
                .date(date)
                .expireDate(expireDate)
                .build();
    }

    public DetailEntity toDetailEntity(){
        return DetailEntity.builder()
                .owner(owner)
                .eventId(eventId)
                .earnId(earnId)
                .point(point)
                .date(date)
                .type(type)
                .build();
    }
}
