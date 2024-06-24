package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


	@SpringBootApplication
	public class DemoApplication extends SpringBootServletInitializer {

		public static void main(String[] args) {
			SpringApplication.run(DemoApplication.class, args);

		}

		//重写configure方法，否则在部署到tomcat时，接口将访问不到
		@Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
			return application.sources(DemoApplication.class);
		}
	}

