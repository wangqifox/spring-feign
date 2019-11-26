package p1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description:
 * @Author: wangqi
 * @Version:
 * @Date: 2019/11/25 4:23 下午
 */
public class Main {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Client client = context.getBean(Client.class);
        System.out.println(client.get1());
    }
}
