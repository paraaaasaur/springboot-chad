package com.herbivore.demo.myapp.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfig {

	private EntityManager entityManager;

	protected JpaConfig() {}

	@Autowired
	protected JpaConfig(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Bean
	public PersistenceUtil getPersistenceUtil() {
		return entityManager.getEntityManagerFactory().getPersistenceUnitUtil();
	}
}
