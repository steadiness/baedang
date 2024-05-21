package com.project.baedang.domain.batch;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyTickers {

    @Id
    private String cik;
    private String name;
    private String ticker;
    private String exchange;
}
