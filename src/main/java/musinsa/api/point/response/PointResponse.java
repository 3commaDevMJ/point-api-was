package musinsa.api.point.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import musinsa.api.point.entity.EventEntity;
import musinsa.common.config.StatusEnum;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@NoArgsConstructor
public class PointResponse {

    private Long owner;
    private String date;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime expireDate;
    private int point;
    private String type;

    @Builder
    private PointResponse(Long owner, String date, LocalDateTime expireDate, int point, String type) {
        this.owner = owner;
        this.date = date;
        this.expireDate = expireDate;
        this.point = point;
        this.type = type;
    }

    PointResponse toResponse(EventEntity entity) {
        return PointResponse.builder()
                .owner(entity.getOwner())
                .expireDate(entity.getExpireDate())
                .date(entity.getDate())
                .point(entity.getPoint())
                .type(entity.getType())
                .build();
    }


    public List<PointResponse> toResponseList(List<EventEntity> entityList) {
        return entityList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}
