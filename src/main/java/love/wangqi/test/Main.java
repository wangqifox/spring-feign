package love.wangqi.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * @author: wangqi
 * @description:
 * @date: Created in 2019-01-11 09:14
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Config.class);
//        ArticleService articleService = annotationConfigApplicationContext.getBean(ArticleService.class);
//        articleService.test();
        ArticleClient articleClient = annotationConfigApplicationContext.getBean(ArticleClient.class);
        Map comments = articleClient.getComments("222131", "123", null, null);
        System.out.println(comments);

    }
}
