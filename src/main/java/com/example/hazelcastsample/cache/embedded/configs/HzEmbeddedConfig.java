package com.example.hazelcastsample.cache.embedded.configs;

import com.example.hazelcastsample.cache.embedded.domains.HzCacheDomain;
import com.example.hazelcastsample.cache.embedded.domains.HzMapStoreDomain;
import com.example.hazelcastsample.commons.models.Student;
import com.hazelcast.config.*;
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
                .setInstanceName("hz-embedded-instance")
                .setClusterName("hz-embedded-cluster")
                .addMapConfig(addStudentMapConfig())
                .setRestConfig(setRestConfig());
    }

    private RestConfig setRestConfig() {
        return new RestConfig()
                .setEnabled(true);
    }

    private MapConfig addStudentMapConfig() {
        return new MapConfig()
                .setName(STUDENT_CACHE.getCacheName())
                .setAsyncBackupCount(2)
                .setTimeToLiveSeconds(100000)
                .setMaxIdleSeconds(100000)
                .setEvictionConfig(new EvictionConfig()
                        .setEvictionPolicy(EvictionPolicy.LRU)
                        .setMaxSizePolicy(MaxSizePolicy.USED_HEAP_PERCENTAGE)
                        .setSize(70))
                .setMapStoreConfig(new MapStoreConfig()
                        .setEnabled(true)
                        .setWriteDelaySeconds(60)
                        .setImplementation(hzMapStoreDomain));
    }

    @Bean("HzEmbeddedStudentCacheDomain")
    public HzCacheDomain<String, Student> HzEmbeddedStudentCacheDomain() {
        return new HzCacheDomain<>(STUDENT_CACHE, this.hazelcastInstance());
    }
}
