package pers.lqresier.dis.demo.dubbo.provider.config;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/7/4 14:30
 * Description
 */
//@Configuration
@EnableDubbo
@PropertySource("classpath:annotation-provider.properties")
@DubboComponentScan({"pers.lqresier.dis.demo.dubbo.provider.service.impl"})
public class AnnotationProviderConfiguration {
}
