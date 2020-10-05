package pers.lqresier.dis.demo.zookeeper;

import pers.lqresier.dis.demo.zookeeper.utils.ZooKeeperLock;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/5/11 13:49
 * Description
 */
public class DemoMain {
    public static void main(String[] args) {
        ZooKeeperLock zooKeeperLock = new ZooKeeperLock("test");
        try {
            if(zooKeeperLock.lock(3000)){
                try{
                    //doSomeThing
                    Thread.sleep(3000);
                }finally {
                    //释放锁
                    zooKeeperLock.unlock();
                }
            }else{
                System.out.println("获取锁失败");
            }
        } catch (Exception e) {
            System.out.println("获取锁失败");
            e.printStackTrace();
        }
    }
}
