package pers.lqresier.dis.demo.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import pers.lqresier.dis.demo.dubbo.provider.config.AnnotationProviderConfiguration;

import java.io.IOException;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/7/4 17:16
 * Description
 */
@EnableDubbo
@PropertySource("classpath:annotation-provider.properties")
@DubboComponentScan({"pers.lqresier.dis.demo.dubbo.provider.service.impl"})
public class AnnotationProviderMain {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationProviderConfiguration.class);
        context.start();
        System.out.println("provider已启动");
        System.in.read();
    }
}
