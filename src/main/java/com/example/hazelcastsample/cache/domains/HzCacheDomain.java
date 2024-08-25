package com.example.hazelcastsample.cache.domains;

import com.example.hazelcastsample.cache.configs.HzEmbeddedConfig;
import com.example.hazelcastsample.cache.services.CacheService;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class HzCacheDomain<V> implements CacheService<V> {
    private IMap<String, V> cache;
    public HzCacheDomain(HzEmbeddedConfig.Caches hzCache, HazelcastInstance hazelcastInstance) {
        this.cache = hazelcastInstance.getMap(hzCache.getCacheName());
    }

    @Override
    public V save(String key, V value) {
        return cache.put(key, value);
    }

    @Override
    public V get(String key) {
        return cache.get(key);
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public boolean contains(String key) {
        return cache.containsKey(key);
    }

}
