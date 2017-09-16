package cn.yjxxclub.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Author: Starry.Teng
 * Email: tengxing7452@163.com
 * Date: 17-9-13
 * Time: 上午11:42
 * Describe:RedisConfig
 */
@Configuration
public class RedisConfig {

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

}
