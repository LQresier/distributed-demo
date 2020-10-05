package pers.lqresier.dis.demo.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/7/2 17:21
 * Description
 */
public class ProviderMain {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:provider.xml");
        context.start();
        System.out.println("provider已启动");
        System.in.read();
    }
}
