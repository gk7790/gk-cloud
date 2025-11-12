package com.gk.common.config;

import com.gk.common.handler.RequestMapResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final RequestMapResolver requestMapResolver;

    public WebConfig(RequestMapResolver requestMapResolver) {
        this.requestMapResolver = requestMapResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(requestMapResolver);
    }
}
