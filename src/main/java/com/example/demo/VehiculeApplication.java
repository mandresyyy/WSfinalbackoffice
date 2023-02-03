package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
@RestController
@SpringBootApplication
@CrossOrigin
public class VehiculeApplication {
	// @Bean
    // public WebMvcConfigurer corsConfigurer(){
    //     return new WebMvcConfigurer() {
    //         @Override
    //         public void addCorsMappings(CorsRegistry registry) {
    //             WebMvcConfigurer.super.addCorsMappings(registry);
    //             registry.addMapping("/**").allowedOrigins("GET","POST","PUT","DELETE","OPTIONS").allowedMethods("*").allowedHeaders("*").maxAge(-1).allowCredentials(false);
    //         }
    //     };
    // }
	public static void main(String[] args) {
		SpringApplication.run(VehiculeApplication.class, args);
	}
	// @GetMapping("/coucou")
	// public String hello() {
	// 	return "hello world";
	// }


}
