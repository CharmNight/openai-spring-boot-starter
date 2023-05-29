package com.night.openai.service.annotation;

import com.night.openai.service.config.DefaultConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@Import({DefaultConfig.class})
public @interface EnableAi {
}
