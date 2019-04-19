package io.github.wangqifox.feign;

import feign.*;
import io.github.wangqifox.feign.support.AnnotatedParameterProcessor;
import io.github.wangqifox.feign.support.SpringMvcContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangqi
 * @description:
 * @date: Created in 2019-01-10 17:30
 */
@Configuration
public class FeignClientsConfiguration {
    @Autowired(required = false)
    private List<AnnotatedParameterProcessor> parameterProcessors = new ArrayList<>();

    @Autowired(required = false)
    private List<FeignFormatterRegistrar> feignFormatterRegistrars = new ArrayList<>();

    @Autowired(required = false)
    private Logger logger;

//    @Bean
//    public Decoder feignDecoder() {
//        return new JacksonDecoder();
//    }
//
//    @Bean
//    public Encoder feignEncoder() {
//        return new JacksonEncoder();
//    }

    @Bean
    public Contract feignContract(ConversionService feignConversionService) {
        return new SpringMvcContract(this.parameterProcessors, feignConversionService);
    }

    @Bean
    public FormattingConversionService feignConversionService() {
        FormattingConversionService conversionService = new DefaultFormattingConversionService();
        for (FeignFormatterRegistrar feignFormatterRegistrar : feignFormatterRegistrars) {
            feignFormatterRegistrar.registerFormatters(conversionService);
        }
        return conversionService;
    }

    @Bean
    public Retryer feignRetryer() {
        return Retryer.NEVER_RETRY;
    }

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder(Retryer retryer) {
        return Feign.builder().retryer(retryer);
    }

    @Bean
    public FeignLoggerFactory feignLoggerFactory() {
        return new DefaultFeignLoggerFactory(logger);
    }
}
