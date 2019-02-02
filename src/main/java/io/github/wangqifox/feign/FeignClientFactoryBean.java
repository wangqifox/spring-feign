package io.github.wangqifox.feign;

import feign.*;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import okhttp3.ConnectionPool;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author: wangqi
 * @description:
 * @date: Created in 2019-01-10 17:13
 */
public class FeignClientFactoryBean implements FactoryBean<Object>, InitializingBean, ApplicationContextAware {

    private Class<?> type;

    private String name;

    private String url;

    private String path;

    private ApplicationContext applicationContext;


    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(this.url, "url must be set");
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }

    protected Feign.Builder feign(FeignContext context) {
        FeignLoggerFactory loggerFactory = get(context, FeignLoggerFactory.class);
        Logger logger = loggerFactory.create(this.type);

        Feign.Builder builder = get(context, Feign.Builder.class)
                // required values
                .logger(logger)
                .client(client())
                .encoder(get(context, Encoder.class))
                .decoder(get(context, Decoder.class))
                .contract(get(context, Contract.class));

        configureFeign(context, builder);

        return builder;
    }

    Client client() {
        ConnectionPool connectionPool = new ConnectionPool(500, 10, TimeUnit.SECONDS);
        okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder()
                .connectionPool(connectionPool)
                .connectTimeout(60, TimeUnit.SECONDS);
        okhttp3.OkHttpClient okHttpClient = builder.build();
        return new OkHttpClient(okHttpClient);
    }

    protected void configureFeign(FeignContext context, Feign.Builder builder) {
        configureUsingConfiguration(context, builder);
    }

    protected void configureUsingConfiguration(FeignContext context, Feign.Builder builder) {
        Logger.Level level = getOptional(context, Logger.Level.class);
        if (level != null) {
            builder.logLevel(level);
        }
        Retryer retryer = getOptional(context, Retryer.class);
        if (retryer != null) {
            builder.retryer(retryer);
        }
        ErrorDecoder errorDecoder = getOptional(context, ErrorDecoder.class);
        if (errorDecoder != null) {
            builder.errorDecoder(errorDecoder);
        }
        Request.Options options = getOptional(context, Request.Options.class);
        if (options != null) {
            builder.options(options);
        }
        Map<String, RequestInterceptor> requestInterceptors = context.getInstances(
                this.name, RequestInterceptor.class);
        if (requestInterceptors != null) {
            builder.requestInterceptors(requestInterceptors.values());
        }
    }


    protected <T> T get(FeignContext context, Class<T> type) {
        T instance = context.getInstance(this.name, type);
        if (instance == null) {
            throw new IllegalStateException("No bean found of type " + type + " for "
                    + this.name);
        }
        return instance;
    }

    protected <T> T getOptional(FeignContext context, Class<T> type) {
        return context.getInstance(this.name, type);
    }

    @Override
    public Object getObject() throws Exception {
        return getTarget();
    }

    <T> T getTarget() {
        FeignContext context = applicationContext.getBean(FeignContext.class);
        Feign.Builder builder = feign(context);

        if (!this.url.startsWith("http")) {
            this.url = "http://" + this.url;
        }
        String url = this.url + cleanPath();

        Targeter targeter = get(context, Targeter.class);
        return (T) targeter.target(this, builder, context, new Target.HardCodedTarget<>(
                this.type, this.name, url));
    }

    private String cleanPath() {
        String path = this.path.trim();
        if (StringUtils.hasLength(path)) {
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (path.endsWith("/")) {
                path = path.substring(0, path.length() - 1);
            }
        }
        return path;
    }

    @Override
    public Class<?> getObjectType() {
        return this.type;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FeignClientFactoryBean that = (FeignClientFactoryBean) o;
        return Objects.equals(applicationContext, that.applicationContext) &&
                Objects.equals(type, that.type) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationContext, type, url);
    }

    @Override
    public String toString() {
        return new StringBuilder("FeignClientFactoryBean{")
                .append("type=").append(type).append(", ")
                .append("url='").append(url).append("', ")
                .append("applicationContext=").append(applicationContext).append(", ")
                .append("}").toString();
    }
}
