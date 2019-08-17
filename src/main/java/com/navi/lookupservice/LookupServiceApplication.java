package com.navi.lookupservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
@RestController
@SpringBootApplication
public class LookupServiceApplication {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(LookupServiceApplication.class, args);
	}

	//By default hikari cp lazy initialized, To force initialization on application startup, explicit bean for jdbcTemplate is done
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource, false);
	}
}
