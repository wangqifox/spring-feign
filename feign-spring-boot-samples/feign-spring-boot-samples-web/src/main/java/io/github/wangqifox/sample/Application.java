package io.github.wangqifox.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description:
 * @Author: wangqi
 * @Version:
 * @Date: 2019/11/26 11:15 上午
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        final Client client = context.getBean(Client.class);
        System.out.println(client.get1());
    }
}
