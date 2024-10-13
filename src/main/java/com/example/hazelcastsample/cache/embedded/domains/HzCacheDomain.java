package com.example.hazelcastsample.cache.embedded.domains;

import com.example.hazelcastsample.cache.embedded.services.CacheService;
import com.example.hazelcastsample.commons.utils.Caches;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class HzCacheDomain<K, V> implements CacheService<K, V> {
    private IMap<K, V> cache;
    @Getter
    private HazelcastInstance hazelcastInstance;
    public HzCacheDomain(Caches hzCache, HazelcastInstance hazelcastInstance) {
        this.cache = hazelcastInstance.getMap(hzCache.getCacheName());
        this.hazelcastInstance = hazelcastInstance;
    }

    @Override
    public V save(K key, V value)       {
        return cache.put(key, value);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public boolean contains(K key) {
        return cache.containsKey(key);
    }

}
