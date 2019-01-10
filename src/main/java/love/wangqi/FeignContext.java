package love.wangqi;

/**
 * @author: wangqi
 * @description:
 * @date: Created in 2019-01-10 17:30
 */
public class FeignContext extends NamedContextFactory<FeignClientSpecification> {

    public FeignContext() {
        super(FeignClientsConfiguration.class, "feign", "feign.client.name");
    }

}