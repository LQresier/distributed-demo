package pers.lqresier.dis.demo.dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.lqresier.dis.demo.dubbo.provider.service.DemoService;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/7/2 17:25
 * Description
 */
public class ConsumerMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:consumer.xml");
        context.start();
        // 获取远程服务代理
        DemoService demoService = (DemoService)context.getBean("demoService");
        // 执行远程方法
        String hello = demoService.sayHello("world");
        // 显示调用结果
        System.out.println( hello );
    }
}
