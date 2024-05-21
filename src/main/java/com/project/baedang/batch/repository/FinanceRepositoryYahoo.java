package com.project.baedang.batch.repository;

import com.project.baedang.domain.batch.CompanyTickers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceRepositoryYahoo extends JpaRepository<CompanyTickers, String> {
}
