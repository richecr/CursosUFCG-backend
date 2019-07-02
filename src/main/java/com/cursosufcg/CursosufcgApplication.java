package com.cursosufcg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.cursosufcg.authentication.TokenFilter;


import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class CursosufcgApplication {
	

	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}
	
	@Bean
	public FilterRegistrationBean<TokenFilter> filterJwt() {
		try {
			FilterRegistrationBean<TokenFilter> filterRb = new FilterRegistrationBean<>();
			filterRb.setFilter(new TokenFilter());
			System.out.println("OKOK");
			filterRb.addUrlPatterns("/v1/perfil/*");
			filterRb.addUrlPatterns("/v1/disciplina/*");
			filterRb.addUrlPatterns("/v1/comentario/*");
			
			return filterRb;
		} catch (Exception e) {
			throw new RuntimeException("Token incorreto ou expirado!");
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CursosufcgApplication.class, args);
	}

}
