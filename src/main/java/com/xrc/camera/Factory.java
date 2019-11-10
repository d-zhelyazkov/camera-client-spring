package com.xrc.camera;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryListener;

import java.util.List;

@Configuration
public class Factory {

    @Bean
    public List<RetryListener> retryListeners() {

        return List.of(
                new RetryLogger()
        );
    }
}
