package musinsa.api.point.response;

import lombok.Getter;
import lombok.Setter;
import musinsa.common.config.StatusEnum;

@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String code;

    public ErrorResponse(StatusEnum statusEnum){
        this.status = statusEnum.getStatusCode();
        this.code = statusEnum.getCode();
    }
}
