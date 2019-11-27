package io.github.wangqifox.sample;

import io.github.wangqifox.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @Author: wangqi
 * @Version:
 * @Date: 2019/11/25 4:26 下午
 */
@FeignClient(name = "client", url = "http://127.0.0.1:8080")
public interface Client {
    @GetMapping("/1")
    String get1();

    @GetMapping("/3")
    String get3(@RequestParam("name") String name);
}
