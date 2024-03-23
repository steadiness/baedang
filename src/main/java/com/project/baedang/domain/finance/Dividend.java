package com.project.baedang.domain.finance;

import com.project.baedang.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Dividend extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "dividend_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stocks_id")
    private Stock stock;
}
