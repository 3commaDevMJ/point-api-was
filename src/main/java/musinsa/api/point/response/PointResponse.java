package musinsa.api.point.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import musinsa.common.config.StatusEnum;

@Getter
@ToString
@NoArgsConstructor
public class PointResponse {

    private Long owner;
    private String date;



}
