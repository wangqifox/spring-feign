package io.github.wangqifox.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangqi
 * @description:
 * @date: Created in 2019-01-11 19:03
 */
@Configuration
@ComponentScan("io.github.wangqifox")
public class FeignAutoConfiguration {
    @Autowired(required = false)
    private List<FeignClientSpecification> configurations = new ArrayList<>();

    @Bean
    public FeignContext feignContext() {
        FeignContext context = new FeignContext();
        context.setConfigurations(this.configurations);
        return context;
    }

    @Configuration
    protected static class DefaultFeignTargeterConfiguration {
        @Bean
        public Targeter feignTargeter() {
            return new DefaultTargeter();
        }
    }


}
