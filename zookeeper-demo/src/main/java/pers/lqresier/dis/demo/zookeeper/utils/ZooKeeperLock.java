package pers.lqresier.dis.demo.zookeeper.utils;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/5/11 13:50
 * Description
 */
public class ZooKeeperLock {
    private static ThreadLocal<InterProcessMutex> mutexThreadLocal = new ThreadLocal<>();
    /**
     * 单点
     */
    private final static String SINGLE_SERVER = "127.0.0.1:2181";
    /**
     * session超时毫秒数
     */
    private final static int SESSION_TIMEOUT = 5000;
    private final static int CONNECTION_TIMEOUT = 5000;
    private static CuratorFramework client;

    static{
        //重试策略，每次重试sleep为1000毫秒，最大重试次数为3次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder()
                //服务地址
                .connectString(SINGLE_SERVER)
                //连接超时
                .connectionTimeoutMs(CONNECTION_TIMEOUT)
                //session超时
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .retryPolicy(retryPolicy)
                .build();
    }

    public boolean lock(String lockKey,long timeout) throws Exception {
        if(mutexThreadLocal.get()==null){
            InterProcessMutex zkMutex = new InterProcessMutex(client, "/" + lockKey+"-lock");
            mutexThreadLocal.set(zkMutex);
        }
        return mutexThreadLocal.get().acquire(timeout, TimeUnit.MILLISECONDS);
    }

    public void unlock() throws Exception {
        InterProcessMutex mutex = mutexThreadLocal.get();
        if (mutex == null) {
            throw new IllegalMonitorStateException("You do not own the lock");
        }
        try {
            mutex.release();
        } finally {
            mutexThreadLocal.remove();
        }
    }
}
