package com.night.openai.service.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Primary
@Bean
public @interface UseAiService {
}
