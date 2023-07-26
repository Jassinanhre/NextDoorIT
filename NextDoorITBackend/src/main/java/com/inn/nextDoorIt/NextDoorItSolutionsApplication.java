package com.inn.nextDoorIt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Duration;

@SpringBootApplication
@EnableCaching
@Slf4j
@EnableScheduling
public class NextDoorItSolutionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NextDoorItSolutionsApplication.class, args);
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        RedisSerializationContext.SerializationPair<Object> serializationPair = RedisSerializationContext.SerializationPair.fromSerializer(jsonRedisSerializer);
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(serializationPair);
        cacheConfiguration.entryTtl(Duration.ofSeconds(30));
        RedisCacheManager cacheManager = RedisCacheManager.builder(RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory)).cacheDefaults(cacheConfiguration).build();
        return cacheManager;
    }


}
