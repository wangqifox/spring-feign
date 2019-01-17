package love.wangqi;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

import java.util.*;

/**
 * @author: wangqi
 * @description:
 * @date: Created in 2019-01-08 11:27
 */
public class FeignConfig3 {
    @Bean
    public RequestInterceptor headerInterceptor() {
        return template -> {
            System.out.println("FeignConfig3");
            String requestId = UUID.randomUUID().toString();


            Map<String, Collection<String>> headers = new HashMap<>();
            headers.put("X-REQUEST-ID", Collections.singletonList(requestId));
            template.headers(headers);
        };
    }
}
