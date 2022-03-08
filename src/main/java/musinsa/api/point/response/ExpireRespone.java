package musinsa.api.point.response;

import java.time.LocalDateTime;

public interface ExpireRespone {
    Long getOwner();
    Long getEarnId();
    int getPoint();
    LocalDateTime getExpireDate();
}
