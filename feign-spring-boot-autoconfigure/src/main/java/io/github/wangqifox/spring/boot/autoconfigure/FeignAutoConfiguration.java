package io.github.wangqifox.spring.boot.autoconfigure;

import io.github.wangqifox.feign.DefaultTargeter;
import io.github.wangqifox.feign.FeignClientSpecification;
import io.github.wangqifox.feign.FeignContext;
import io.github.wangqifox.feign.Targeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
@ComponentScan("io.github.wangqifox")
public class FeignAutoConfiguration {
    @Autowired(required = false)
    private List<FeignClientSpecification> configurations = new ArrayList<>();

    @Bean
    public FeignContext feignContext() {
        FeignContext context = new FeignContext(FeignClientsConfiguration.class);
        context.setConfigurations(this.configurations);
        return context;
    }

    @Configuration
    protected static class DefaultFeignTargeterConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public Targeter feignTargeter() {
            return new DefaultTargeter();
        }
    }


}
