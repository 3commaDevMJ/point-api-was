package musinsa.api.point.request;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import musinsa.api.point.entity.DetailEntity;
import musinsa.api.point.entity.EventEntity;
import musinsa.common.config.EnumPattern;
import musinsa.common.config.PointEnum;

import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PointRequest {
    @NotNull
    private Long owner;
    @NotNull
    @Positive
    private int point;

    @EnumPattern(enumClass = PointEnum.class, ignoreCase = true)
    private String type;

    private String date = LocalDate.now().toString();
    private LocalDateTime expireDate = LocalDateTime.now().plusYears(1);

    //detail
    private Long earnId;
    private Long eventId;

    @Builder
    public PointRequest (Long owner, int point, String type, String date, LocalDateTime expireDate){
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
