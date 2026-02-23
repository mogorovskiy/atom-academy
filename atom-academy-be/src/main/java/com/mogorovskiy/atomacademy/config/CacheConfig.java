package com.mogorovskiy.atomacademy.config;

import com.mogorovskiy.atomacademy.domain.CacheNames;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class CacheConfig {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisSerializer<Object> jsonSerializer = RedisSerializer.json();

        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer))
                .entryTtl(Duration.ofMinutes(10))
                .disableCachingNullValues();

        Map<String, RedisCacheConfiguration> cacheConfigs = new HashMap<>();
        cacheConfigs.put(CacheNames.COURSE, defaultConfig.entryTtl(Duration.ofHours(1)));
        cacheConfigs.put(CacheNames.QUESTION, defaultConfig.entryTtl(Duration.ofMinutes(30)));

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(defaultConfig)
                .withInitialCacheConfigurations(cacheConfigs)
                .transactionAware()
                .build();
    }
}
