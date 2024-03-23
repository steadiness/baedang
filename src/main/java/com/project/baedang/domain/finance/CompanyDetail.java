package com.project.baedang.domain.finance;

import com.project.baedang.domain.common.BaseEntity;
import com.project.baedang.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class CompanyDetail extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "company_detail_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stocks_id")
    private Stock stock;
}
