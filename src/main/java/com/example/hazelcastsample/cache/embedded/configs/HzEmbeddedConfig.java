package com.example.hazelcastsample.cache.embedded.configs;

import com.example.hazelcastsample.cache.embedded.domains.HzCacheDomain;
import com.example.hazelcastsample.cache.embedded.domains.HzMapStoreDomain;
import com.example.hazelcastsample.commons.models.Student;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.config.rest.RestConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.hazelcastsample.commons.utils.Caches.STUDENT_CACHE;

@Configuration
@Slf4j
public class HzEmbeddedConfig {

    private final HzMapStoreDomain hzMapStoreDomain;
    public HzEmbeddedConfig(HzMapStoreDomain hzMapStoreDomain) {
        this.hzMapStoreDomain = hzMapStoreDomain;
    }

    @Bean(name = "hzEmbeddedInstance")
    public HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance(setConfig());
    }

    private Config setConfig() {
        return new Config()
                .setInstanceName("hz-sample-instance")
                .setClusterName("hz-sample-cluster")
                .addMapConfig(new MapConfig()
                        .setName(STUDENT_CACHE.getCacheName())
                        .setMapStoreConfig(new MapStoreConfig()
                                .setEnabled(true)
                                .setWriteDelaySeconds(60)
                                .setImplementation(hzMapStoreDomain)))
                .setRestConfig(new RestConfig()
                        .setEnabled(true))
                ;
    }

    @Bean("HzEmbeddedStudentCacheDomain")
    public HzCacheDomain<String, Student> HzEmbeddedStudentCacheDomain() {
        return new HzCacheDomain<>(STUDENT_CACHE, this.hazelcastInstance());
    }
}
