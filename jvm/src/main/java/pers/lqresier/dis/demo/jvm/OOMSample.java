package pers.lqresier.dis.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/7/12 15:24
 * 内存溢出
 * jvm参数：-Xms100m -Xmx100m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\Users\10253\Desktop\oomSample.hprof
 */
public class OOMSample {

    static void headOutOfMemory(){
        long count= 0;
        try {
            List<Object> objects = new ArrayList<Object>();
            while (true) {
                count++;
                objects.add(new Object());
            }
        } catch(Throwable ex) {
            System.out.println(count);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        headOutOfMemory();
    }
}
