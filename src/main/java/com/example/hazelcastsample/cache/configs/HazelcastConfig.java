package com.example.hazelcastsample.cache.configs;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class HazelcastConfig {

    @Bean
    public HazelcastInstance hazelcastInstance() {
        return this.getHazelcastInstance();
    }

    private HazelcastInstance getHazelcastInstance() {
        return Hazelcast.newHazelcastInstance(setConfig());
    }

    private Config setConfig() {
        return new Config();
    }

}
