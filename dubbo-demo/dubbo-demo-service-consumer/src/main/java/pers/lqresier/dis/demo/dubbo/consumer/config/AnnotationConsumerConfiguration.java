package pers.lqresier.dis.demo.dubbo.consumer.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/7/4 14:57
 * Description
 */
//@Configuration
@EnableDubbo
@PropertySource("classpath:annotation-consumer.properties")
@ComponentScan({"pers.lqresier.dis.demo.dubbo.consumer.action"})
public class AnnotationConsumerConfiguration {

}
