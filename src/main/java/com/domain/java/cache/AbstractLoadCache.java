package com.domain.java.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * com.domain.java.cache
 * @author Mark Li
 * @version 1.0.0
 * @since 2015-12-17
 */
public abstract class AbstractLoadCache<K, V> {

    LoadingCache<K, V> cache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(new CacheLoader<K, V>() {
                @Override
                public V load(K key) throws Exception {

                    return getData(key);
                }
            });

    protected abstract V getData(K key);

    public LoadingCache<K, V> getCache() {

        return cache;
    }

    public void setCache(LoadingCache<K, V> cache) {

        this.cache = cache;
    }
}
