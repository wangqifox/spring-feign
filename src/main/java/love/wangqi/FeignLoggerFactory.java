package love.wangqi;

import feign.Logger;

/**
 * @author: wangqi
 * @description:
 * @date: Created in 2019-01-10 18:18
 */
public interface FeignLoggerFactory {
    public Logger create(Class<?> type);
}
