package com.example.hazelcastsample.cache.configs;

import com.example.hazelcastsample.cache.domains.CacheDomain;
import com.example.hazelcastsample.cache.models.Student;
import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.hazelcastsample.cache.configs.HazelcastConfig.Caches.STUDENT_CACHE;

@Configuration
@Slf4j
public class HazelcastConfig {

    @Bean
    public HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance(setConfig());
    }

    private Config setConfig() {
        return new ClasspathXmlConfig("hazelcast-config.xml");
    }

    @Bean("studentCacheDomain")
    public CacheDomain<Student> studentCacheDomain() {
        return new CacheDomain<>(STUDENT_CACHE, this.hazelcastInstance());
    }

    @Getter
    public enum Caches {
        STUDENT_CACHE("student-cache");

        private final String cacheName;
        Caches(String name) {
            this.cacheName = name;
        }
    }
}
