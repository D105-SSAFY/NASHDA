package com.ssafy.nashda.simulGPT.config;

import feign.RequestInterceptor;
import lombok.*;
import org.springframework.context.annotation.Bean;

public class ChatGptHeaderConfig {
    @Value("${chatgpt.api-key}")
    String openAIAPIKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", openAIAPIKey);
            requestTemplate.header("Content-")
        }

    }
}
