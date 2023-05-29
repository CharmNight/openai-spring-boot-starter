package com.night.openai.service.config;

import com.night.openai.service.annotation.ConditionalOnMissingAiService;
import com.night.openai.service.properties.DefaultProperties;
import com.night.openai.service.server.ai.openai.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(DefaultProperties.class)
public class DefaultConfig {
    private final DefaultProperties properties;

    @Bean
    @ConditionalOnMissingAiService
    public OpenAiService defaultOpenAiService() {
        assert properties != null && !properties.getToken().trim().equals("");
        return new OpenAiService(properties);
    }

}
