package com.project.baedang.domain.finance;


import com.project.baedang.domain.common.BaseEntity;
import com.project.baedang.domain.user.Portfolio;
import com.project.baedang.domain.user.PortfolioDetail;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "stocks")
public class Stock extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "stocks_id")
    private Long id;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<CompanyDetail> companyDetails = new ArrayList<>();

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<Dividend> dividends = new ArrayList<>();

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<Portfolio> portfolios = new ArrayList<>();

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<PortfolioDetail> portfolioDetails = new ArrayList<>();

}
