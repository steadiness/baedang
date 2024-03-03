package com.project.baedang;

import com.project.baedang.entity.QTestEntity;
import com.project.baedang.entity.TestEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class BaedangApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {

		TestEntity testEntity = new TestEntity();
		em.persist(testEntity);

		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
		QTestEntity qTestEntity = QTestEntity.testEntity;

		TestEntity result = jpaQueryFactory
				.selectFrom(qTestEntity)
				.fetchOne();

		Assertions.assertThat(result).isEqualTo(testEntity);
		//lombok 동작 확인
		Assertions.assertThat(result.getId()).isEqualTo(testEntity.getId());
	}

}
