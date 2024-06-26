package com.project.baedang.domain.user;

import com.project.baedang.domain.common.BaseEntity;
import com.project.baedang.domain.finance.Stock;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Portfolio extends BaseEntity {

    // 포트폴리오 요약본 (포트폴리오ID, 유저ID, 현재자산, 예수금, 총평가손익, 당일실현손익, 총매입금액, 담보비율, 총평가금액, 전체배당금, 이익실현배당금)
    @Id
    @GeneratedValue
    @Column(name = "portfolio_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stocks_id")
    private Stock stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

}
