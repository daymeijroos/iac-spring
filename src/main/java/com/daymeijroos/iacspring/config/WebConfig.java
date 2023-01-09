package com.daymeijroos.iacspring.config;

import com.daymeijroos.iacspring.formatter.ProductFilterFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ProductFilterFormatter());
    }
}