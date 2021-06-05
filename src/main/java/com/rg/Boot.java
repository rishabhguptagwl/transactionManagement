package com.rg;

import java.net.InetAddress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.rg.common.CommonConstants;

@SpringBootApplication
public class Boot extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Boot.class);
	}
	
	
	public static void main(String[] args) throws Exception {
		InetAddress inetAddress = InetAddress.getLocalHost();
		CommonConstants.ip = inetAddress.getHostAddress();
		SpringApplication.run(Boot.class, args);
	}
	
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("http://localhost:8080","http://localhost:4200");
			}
		};
	}

	
	
	
}
