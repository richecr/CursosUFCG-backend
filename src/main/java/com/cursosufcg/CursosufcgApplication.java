package com.cursosufcg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.cursosufcg.authentication.TokenFilter;

@SpringBootApplication
public class CursosufcgApplication {

	@Bean
	public FilterRegistrationBean<TokenFilter> filterJwt() {
		try {
			FilterRegistrationBean<TokenFilter> filterRb = new FilterRegistrationBean<>();
			filterRb.setFilter(new TokenFilter());
			System.out.println("OKOK");
			filterRb.addUrlPatterns("/v1/sem/*");
			
			return filterRb;
		} catch (Exception e) {
			throw new RuntimeException("Token incorreto ou expirado!");
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CursosufcgApplication.class, args);
	}

}
