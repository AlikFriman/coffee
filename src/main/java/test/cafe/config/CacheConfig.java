package test.cafe.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {

    public static final String COFFEE_TYPE_SERVICE_LIST = "CoffeeTypeService#list";
    public static final String COFFEE_TYPE_SERVICE_GET = "CoffeeTypeService#getById";

    @Bean
    @Primary
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        cacheManager.setCacheNames(
                List.of(
                        COFFEE_TYPE_SERVICE_LIST,
                        COFFEE_TYPE_SERVICE_GET
                )
        );
        return cacheManager;
    }

    @Scheduled(fixedRateString = "60000")
    @CacheEvict(cacheNames = {COFFEE_TYPE_SERVICE_LIST, COFFEE_TYPE_SERVICE_GET}, allEntries = true)
    public void evictCache() {
        log.info("Cache evicted");
    }
}
