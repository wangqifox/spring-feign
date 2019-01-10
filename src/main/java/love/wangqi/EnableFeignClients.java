package love.wangqi;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: wangqi
 * @description:
 * @date: Created in 2019-01-10 16:37
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(FeignClientsRegistrar.class)
public @interface EnableFeignClients {
}
