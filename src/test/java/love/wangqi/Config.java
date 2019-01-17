package love.wangqi;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Decoder;
import feign.codec.Encoder;
import io.github.wangqifox.feign.EnableFeignClients;
import io.github.wangqifox.feign.FeignAutoConfiguration;
import io.github.wangqifox.feign.support.ResponseEntityDecoder;
import io.github.wangqifox.feign.support.SpringDecoder;
import io.github.wangqifox.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Collections;

/**
 * @author: wangqi
 * @description:
 * @date: Created in 2019-01-11 09:14
 */
@Configuration
@ComponentScan("love.wangqi")
@EnableFeignClients
@Import(FeignAutoConfiguration.class)
public class Config {
    @Bean
    public Decoder feignDecoder() {
        HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(new ObjectMapper());
        return new ResponseEntityDecoder(new SpringDecoder(Collections.singletonList(jacksonConverter)));
    }

    @Bean
    public Encoder feignEncoder() {
        HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(new ObjectMapper());
        return new SpringEncoder(Collections.singletonList(jacksonConverter));
    }
}
