package pers.lqresier.dis.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/7/11 22:31
 * 测试jconsole内存页
 * 虚拟机参数：-Xms100M -Xms100m -XX:+UseSerialGC -XX:+PrintGCDetails
 */
public class JConsoleToolMemory {

    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        //先运行程序，再执行监控
        Thread.sleep(20000);
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
            // 稍作延时，令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
        while(true){
            //让其一直运行着
        }
    }
}
