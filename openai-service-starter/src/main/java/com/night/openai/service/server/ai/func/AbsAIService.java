package com.night.openai.service.server.ai.func;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.night.openai.service.server.ai.openai.OpenAiApi;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public abstract class AbsAIService implements AIService {

    /**
     * 默认序列化
     * @return
     */
    public static ObjectMapper defaultObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return mapper;
    }

    /**
     * 默认请求连接器
     * @param token
     * @param timeout
     * @return
     */
    public static OkHttpClient defaultClient(String token, Integer timeout) {
        return new OkHttpClient.Builder()
                .addInterceptor(new AuthenticationInterceptor(token))
                .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
                .readTimeout(Duration.ofSeconds(timeout).toMillis(), TimeUnit.MILLISECONDS)
                .build();
    }

    /**
     * 默认请求框架
     * @param client
     * @param mapper
     * @return
     */
    public static Retrofit defaultRetrofit(OkHttpClient client, ObjectMapper mapper, String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
