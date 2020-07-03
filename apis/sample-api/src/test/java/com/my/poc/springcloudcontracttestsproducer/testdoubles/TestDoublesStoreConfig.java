package com.my.poc.springcloudcontracttestsproducer.testdoubles;

import com.my.poc.user.UserStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TestDoublesStoreConfig {

    @Bean
    @Primary
    public UserStore userStoreStub() {
        return new UserStoreStub();
    }
}
