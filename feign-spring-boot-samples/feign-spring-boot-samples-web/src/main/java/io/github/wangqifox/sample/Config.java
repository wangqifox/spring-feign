package io.github.wangqifox.sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import io.github.wangqifox.feign.EnableFeignClients;
import io.github.wangqifox.feign.support.ResponseEntityDecoder;
import io.github.wangqifox.feign.support.SpringDecoder;
import io.github.wangqifox.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author: wangqi
 * @description:
 * @date: Created in 2019-01-11 09:14
 */
@Configuration
@EnableFeignClients
public class Config {
    @Bean
    public Decoder feignDecoder() {
        HttpMessageConverter<Object> jacksonConverter = new MappingJackson2HttpMessageConverter(new ObjectMapper());
        final StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        return new ResponseEntityDecoder(new SpringDecoder(Arrays.asList(jacksonConverter, stringHttpMessageConverter)));
    }

    @Bean
    public Encoder feignEncoder() {
        HttpMessageConverter<Object> jacksonConverter = new MappingJackson2HttpMessageConverter(new ObjectMapper());
        return new SpringEncoder(Collections.singletonList(jacksonConverter));
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default();
    }
}
