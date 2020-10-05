package pers.lqresier.dis.demo.dubbo.provider.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import pers.lqresier.dis.demo.dubbo.provider.service.AnnotationService;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/7/4 14:25
 * Description
 */
@DubboService
public class AnnotationServiceImpl implements AnnotationService {
    @Override
    public String eat(String food) {
        return "eat "+food;
    }
}
