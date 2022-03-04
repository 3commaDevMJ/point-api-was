package musinsa.api.point.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import musinsa.common.config.BaseEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name ="point_event")
@Entity
public class EventEntity extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String owner;

    private String type;

    private Long point;

    private String date;

    private String expireDate;
}
