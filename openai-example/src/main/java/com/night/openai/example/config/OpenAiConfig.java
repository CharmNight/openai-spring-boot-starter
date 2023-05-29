package com.night.openai.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.night.openai.service.annotation.UseAiService;
import com.night.openai.service.annotation.EnableAi;
import com.night.openai.service.properties.DefaultProperties;
import com.night.openai.service.server.ai.openai.OpenAiApi;
import com.night.openai.service.server.ai.openai.OpenAiService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Retrofit;

import static com.night.openai.service.server.ai.func.AbsAIService.*;

@EnableAi
public class OpenAiConfig {
    @Autowired
    DefaultProperties properties;

    @UseAiService
    public OpenAiService openAiService(){
        ObjectMapper mapper = defaultObjectMapper();
        OkHttpClient client = defaultClient(properties.getToken(), properties.getDefaultTimeout())
                .newBuilder()
                .build();
        Retrofit retrofit = defaultRetrofit(client, mapper, properties.getUrl());

        OpenAiApi api = retrofit.create(OpenAiApi.class);
        return new OpenAiService(api);
    }
}
