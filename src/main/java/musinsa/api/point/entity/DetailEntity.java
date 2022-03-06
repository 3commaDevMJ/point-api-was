package musinsa.api.point.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import musinsa.common.config.BaseEntity;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "point_detail")
@SequenceGenerator(
        name = "DETAIL_SEQ_GENERATOR",
        sequenceName = "DETAIL_SEQ",
        initialValue = 1,
        allocationSize = 1)
@DynamicUpdate
@Entity
public class DetailEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "DETAIL_SEQ_GENERATOR")
    private Long id;

    private String type;

    private int point;

    private Long eventId;

    private Long earnId;

    private String date;

    private Long owner;

    @Builder
    public DetailEntity(String type,int point,Long eventId,Long earnId,String date,Long owner){
        this.type = type;
        this.point = point;
        this.eventId = eventId;
        this.earnId = earnId;
        this.date = date;
        this.owner = owner;
    }

}
