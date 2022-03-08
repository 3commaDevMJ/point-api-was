package musinsa.api.point.exception;

import lombok.Getter;
import musinsa.common.config.StatusEnum;

@Getter
public class PointException extends RuntimeException{

    private StatusEnum statusEnum;

    public PointException(StatusEnum statusEnum){
        this.statusEnum = statusEnum;
    }
}
