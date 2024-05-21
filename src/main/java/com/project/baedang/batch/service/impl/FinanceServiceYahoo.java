package com.project.baedang.batch.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.baedang.batch.repository.FinanceRepositoryYahoo;
import com.project.baedang.batch.service.FinanceService;
import com.project.baedang.domain.batch.CompanyTickers;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FinanceServiceYahoo implements FinanceService {

    private final FinanceRepositoryYahoo financeRepositoryYahoo;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper;

    @Override
    public void fetchAndStoreData(){


        try{
            //EDGAR company ticker info
            String url = "https://www.sec.gov/files/company_tickers_exchange.json";

            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");
            headers.set("Accept", "application/json");
            headers.set("Accept-Language", "en-US,en;q=0.5");
            headers.set("Connection", "keep-alive");

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, JsonNode.class);
            JsonNode jsonResponse = responseEntity.getBody();

            if (jsonResponse == null) {
                throw new IllegalStateException("Failed to fetch data from " + url);
            }

            JsonNode dataNode = jsonResponse.get("data");

            List<CompanyTickers> companyTickersList = new ArrayList<>();

            for (JsonNode data : dataNode) {
                companyTickersList.add(
                        new CompanyTickers(
                                data.get(0).asText(),
                                data.get(1).asText(),
                                data.get(2).asText(),
                                data.get(3).asText()
                        ));
            }

            financeRepositoryYahoo.saveAll(companyTickersList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
