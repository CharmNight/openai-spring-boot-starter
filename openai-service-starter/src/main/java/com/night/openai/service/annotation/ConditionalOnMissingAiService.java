package com.night.openai.service.annotation;

import com.night.openai.service.core.OnMissingUserServiceCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnMissingUserServiceCondition.class)
public @interface ConditionalOnMissingAiService {
}
