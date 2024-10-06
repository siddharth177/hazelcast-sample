package com.example.hazelcastsample.cache.configs;

import com.example.hazelcastsample.cache.domains.HzCacheDomain;
import com.example.hazelcastsample.cache.domains.HzMapStoreDomain;
import com.example.hazelcastsample.cache.models.Student;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.config.rest.RestConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.hazelcastsample.cache.configs.HzEmbeddedConfig.Caches.STUDENT_CACHE;

@Configuration
@Slf4j
public class HzEmbeddedConfig {

    @Autowired
    HzMapStoreDomain hzMapStoreDomain;

    @Bean(name = "hzEmbeddedInstance")
    public HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance(setConfig());
    }

    private Config setConfig() {
        return new Config()
                .setInstanceName("hz-sample-instance")
                .setClusterName("hz-sample-cluster")
                .addMapConfig(new MapConfig()
                        .setName(STUDENT_CACHE.cacheName)
                        .setMapStoreConfig(new MapStoreConfig()
                                .setEnabled(true)
                                .setWriteDelaySeconds(1000)
                                .setImplementation(hzMapStoreDomain)))
                .setRestConfig(new RestConfig()
                        .setEnabled(true))
                ;
    }

    @Bean("HzEmbeddedStudentCacheDomain")
    public HzCacheDomain<Student> HzEmbeddedStudentCacheDomain() {
        return new HzCacheDomain<>(STUDENT_CACHE, this.hazelcastInstance());
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
