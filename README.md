# spring-feign

[![Build Status](https://travis-ci.org/wangqifox/spring-feign.svg?branch=master)](https://travis-ci.org/wangqifox/spring-feign)
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

`spring-feign`是一个用于接口调用的工具。

## Background

Feign([https://github.com/OpenFeign/feign](https://github.com/OpenFeign/feign))是一个基于注解来生成HTTP请求，并且能自动处理请求返回的工具。

缺点是Feign不支持Spring，这意味着无法享受到Spring依赖注入的便利。`spring-cloud-starter-openfeign`组件对Feign进行了包装，让Feign可以方便的用于Spring。

不过`spring-cloud-starter-openfeign`组件依赖整套`spring-cloud`系统，只有项目中使用`spring-cloud`才能用`spring-cloud-starter-openfeign`组件来请求注册的服务。

很多情况下，我们需要调用一个第三方服务，此时`spring-cloud-starter-openfeign`组件就无法使用了。这个时候我们就需要一个方便的、能够简化http接口请求的工具。`spring-feign`就是这样的工具。

`spring-feign`是对`spring-cloud-starter-openfeign`组件的裁剪与修改，剥离其中有用的部分。

## Usage

1. 在`pom.xml`文件中添加`feign-spring-boot-starter`：

    ```
    <dependency>
        <groupId>io.github.wangqifox</groupId>
        <artifactId>feign-spring-boot-starter</artifactId>
        <version>3.0-SNAPSHOT</version>
    </dependency>
    ```

2. 在配置类中添加`@EnableFeignClients`注释

3. 使用`@FeignClient`注释定义`feign`客户端：

    ```
    @FeignClient(name = "client", url = "http://127.0.0.1:8080")
    public interface Client {
        @GetMapping("/1")
        String get1();
    }
    ```
    
    注意：其中`url`可以是这样的形式`#{my-service.url}`，然后可以在配置文件中添加`my-service.url`，这样`client`对象生成的时候就会去配置文件中读取`my-service.url`配置。

4. 最后就可以在需要使用的地方注入`feign`客户端：

    ```
    @Autowired
    Client client;
    ```

5. 对`Client`中`get1()`方法的调用会由代理类来完成，请求`http://127.0.0.1:8080/1`接口然后返回结果。

## License

[Apache License 2.0](LICENSE)
