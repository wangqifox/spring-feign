package love.wangqi;

import feign.Logger;
import feign.slf4j.Slf4jLogger;

/**
 * @author: wangqi
 * @description:
 * @date: Created in 2019-01-10 18:19
 */
public class DefaultFeignLoggerFactory implements FeignLoggerFactory {

    private Logger logger;

    public DefaultFeignLoggerFactory(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Logger create(Class<?> type) {
        return this.logger != null ? this.logger : new Slf4jLogger(type);
    }
}
