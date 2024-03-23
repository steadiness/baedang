package com.project.baedang.domain.user;

import com.project.baedang.domain.finance.Stock;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class PortfolioDetail {

    // 포트폴리오 상세 테이블 별도 필요 (포트폴리오상세ID, 유저ID, 주식ID, 평가손익, 매도가능, 손익률, 평균단가, 매입금, 보유잔고, 평가금, 현재가, 매수수수료, 매도제비용, 배당예상금액, 현재배당률, 배당날짜, 배당타입)
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
