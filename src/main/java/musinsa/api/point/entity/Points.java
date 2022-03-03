package musinsa.api.point.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import musinsa.common.config.BaseEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Points extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private Long point;
}
