package love.wangqi;

import love.wangqi.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: wangqi
 * @description:
 * @date: Created in 2019-01-11 09:14
 */
@Configuration
@ComponentScan("love.wangqi")
@EnableFeignClients(basePackages = "love.wangqi")
public class Config {
}
