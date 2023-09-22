package com.ssafy.nashda.simulGPT.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class ChatGptHeaderConfig {
    @Value("${chatgpt.api-key}")
    String openAIAPIKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", "Bearer " + openAIAPIKey);
            requestTemplate.header("Content-Type", "application/json");
        };
    }
}
