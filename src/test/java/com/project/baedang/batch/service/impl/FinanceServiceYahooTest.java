package com.project.baedang.batch.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.baedang.batch.repository.FinanceRepositoryYahoo;
import com.project.baedang.domain.batch.CompanyTickers;
import com.project.baedang.domain.batch.QCompanyTickers;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@SpringBootTest
class FinanceServiceYahooTest {

    @PersistenceContext
    EntityManager em;

    @MockBean
    private FinanceRepositoryYahoo financeRepositoryYahoo;
    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private FinanceServiceYahoo financeServiceYahoo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFetchAndStoreData() throws JsonProcessingException {
        System.out.println("### testFetchAndStoreData 실행 ###");

        //given
        String url = "https://www.sec.gov/files/company_tickers_exchange.json";

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");
        headers.set("Accept", "application/json");
        headers.set("Accept-Language", "en-US,en;q=0.5");
        headers.set("Connection", "keep-alive");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String jsonResponseStr = "{\"fields\":[\"cik\",\"name\",\"ticker\",\"exchange\"],\"data\":[[789019,\"MICROSOFT CORP\",\"MSFT\",\"Nasdaq\"],[320193,\"Apple Inc.\",\"AAPL\",\"Nasdaq\"],[1045810,\"NVIDIA CORP\",\"NVDA\",\"Nasdaq\"]]}";
        JsonNode jsonResponse = objectMapper.readTree(jsonResponseStr);
        ResponseEntity<JsonNode> responseEntity = ResponseEntity.ok(jsonResponse);

        when(restTemplate.exchange(url, HttpMethod.GET, entity, JsonNode.class)).thenReturn(responseEntity);

        JsonNode dataNode = jsonResponse.get("data");

        List<CompanyTickers> companyTickersList = new ArrayList<>();

        CompanyTickers givenTicker = null;

        for (JsonNode data : dataNode) {
            if(data.get(2).asText().equals("AAPL")) {
                givenTicker = new CompanyTickers(
                        data.get(0).asText(),
                        data.get(1).asText(),
                        data.get(2).asText(),
                        data.get(3).asText()
                );
            }
        }

        // when
        financeServiceYahoo.fetchAndStoreData();

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QCompanyTickers q = new QCompanyTickers("q");

        CompanyTickers appleTicker = queryFactory
                .select(q)
                .from(q)
                .where(q.ticker.eq("AAPL"))
                .fetchOne();

        // then
        assertThat(appleTicker).isNotNull();
        assertThat(givenTicker).isEqualTo(appleTicker);
    }

}