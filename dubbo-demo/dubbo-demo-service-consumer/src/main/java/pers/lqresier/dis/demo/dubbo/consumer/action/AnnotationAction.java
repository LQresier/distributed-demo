package pers.lqresier.dis.demo.dubbo.consumer.action;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import pers.lqresier.dis.demo.dubbo.provider.service.AnnotationService;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/7/4 15:26
 * Description
 */
@Component("annotationAction")
public class AnnotationAction {
    @DubboReference
    private AnnotationService annotationService;

    public String eat(String food){
        return annotationService.eat(food);
    }
}
