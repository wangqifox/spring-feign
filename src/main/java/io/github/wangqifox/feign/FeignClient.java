package io.github.wangqifox.feign;

import java.lang.annotation.*;

/**
 * @author: wangqi
 * @description:
 * @date: Created in 2019-01-10 16:52
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FeignClient {
    String url() default "";

    String path() default "";

    boolean primary() default true;

    Class<?>[] configuration() default {};
}