package pers.lqresier.dis.demo.dubbo.consumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import pers.lqresier.dis.demo.dubbo.consumer.action.AnnotationAction;
import pers.lqresier.dis.demo.dubbo.consumer.config.AnnotationConsumerConfiguration;

import java.io.IOException;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/7/4 14:55
 * Description
 */
@EnableDubbo
@PropertySource("classpath:annotation-consumer.properties")
@ComponentScan({"pers.lqresier.dis.demo.dubbo.consumer.action"})
public class AnnotationConsumerMain {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConsumerConfiguration.class);
        context.start();
        AnnotationAction annotationAction = (AnnotationAction) context.getBean("annotationAction");
        System.out.println(annotationAction.eat("rice"));
        System.out.println("按任意键继续");
        System.in.read();
        System.out.println(annotationAction.eat("rice"));
    }
}
