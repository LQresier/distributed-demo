package pers.lqresier.dis.demo.dubbo.provider.service.impl;

import pers.lqresier.dis.demo.dubbo.provider.service.DemoService;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/7/2 16:56
 * Description
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello "+name;
    }
}
