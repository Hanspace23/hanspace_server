package com.csee.hanspace.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public static final String ALLOWED_METHOD_NAMES = "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH";

//    @Override
//    public void addCorsMappings(final CorsRegistry registry) {
//        registry.addMapping("/api/**")
//                .allowedMethods(ALLOWED_METHOD_NAMES.split(","))
//                .exposedHeaders(HttpHeaders.LOCATION);
//    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/")
                .allowedOrigins("http://localhost:8080", "http://localhost:8081") // 허용할 출처
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP method
                .allowCredentials(true) // 쿠키 인증 요청 허용
                .maxAge(3000);// 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
    }
}