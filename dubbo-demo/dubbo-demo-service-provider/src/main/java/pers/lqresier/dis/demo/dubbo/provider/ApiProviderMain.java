package pers.lqresier.dis.demo.dubbo.provider;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import pers.lqresier.dis.demo.dubbo.provider.service.DemoService;
import pers.lqresier.dis.demo.dubbo.provider.service.impl.DemoServiceImpl;

import java.io.IOException;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/7/3 0:24
 * Description
 */
public class ApiProviderMain {
    public static void main(String[] args) throws IOException {
        //服务实现
        DemoService demoService=new DemoServiceImpl();
        ApplicationConfig application = new ApplicationConfig();
        application.setName("hello-world-app");

        //连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://localhost:2181");
//        registry.setUsername("username");
//        registry.setPassword("password");

        //协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20880);
        protocol.setThreads(200);

        //服务提供者暴露服务配置
        //PS：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口
        //请自行缓存，否则可能导致内存和连接泄露
        ServiceConfig<DemoService> service = new ServiceConfig<>();
        service.setApplication(application);
        //多注册中心可以用setRegistrys()
        service.setRegistry(registry);
        //多个协议可以用setProtocols()
        service.setProtocol(protocol);
        service.setInterface(DemoService.class);
        service.setRef(demoService);
        service.setVersion("v1.0.0");
        //暴露和注册服务
        service.export();
        System.out.println("provider启动。。");
        System.in.read();
    }
}
