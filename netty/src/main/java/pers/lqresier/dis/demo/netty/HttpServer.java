package pers.lqresier.dis.demo.netty;

import io.netty.bootstrap.ServerBootstrap;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/6/21 18:26
 * Description
 */
public class HttpServer {
    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start(){
        ServerBootstrap serverBootstrap = new ServerBootstrap();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: " + HttpServer.class.getSimpleName() + " <port>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        new HttpServer(port);
    }
}
