package musinsa.api.point.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import musinsa.common.config.BaseEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "point_event")
@SequenceGenerator(
        name = "EVENT_SEQ_GENERATOR",
        sequenceName = "EVENT_SEQ",
        initialValue = 1,
        allocationSize = 1)
@Entity
public class EventEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "EVENT_SEQ_GENERATOR")
    private Long id;

    private Long owner;

    private String type;

    private int point;

    private String date;

    private String expireDate;

    @Builder
    public EventEntity(Long owner, String type, int point, String date, String expireDate) {
        this.owner = owner;
        this.type = type;
        this.point = point;
        this.date = date;
        this.expireDate = expireDate;
    }
}
