package com.night.openai.service.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "openai")
public class DefaultProperties {
    /**
     * OpenAi中的token:sk-XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
     */
    private String token;
    /**
     * 请求地址
     */
    private String url = "https://api.openai.com/";
    /**
     * 超时时间：单位秒
     */
    private Integer defaultTimeout = 10;
}
