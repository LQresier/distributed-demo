package pers.lqresier.dis.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/6/24 11:11
 * Description
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: " + EchoServer.class.getSimpleName() + " ");
        }
        //设置端口值（如果端口参数的格式不正确，则抛出一个NumberFormatException）
        int port = Integer.parseInt(args[0]);
        System.out.println("启动服务器");
        //调用服务器的start()方法
        new EchoServer(port).start();
    }

    public void start() throws Exception {
        final EchoServerHandler handler = new EchoServerHandler();
        //创建EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建ServerBootstrap
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    //指定所使用的NIO传输channel
                    .channel(NioServerSocketChannel.class)
                    //使用指定端口设置套接字地址
                    .localAddress(new InetSocketAddress(port))
                    //添加一个EchoServer
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(handler);
                        }
                    });
            //异步绑定服务器，调用sync()方法阻塞等待直到绑定完成
            ChannelFuture future = b.bind().sync();
            //获取channel的closeFuture，并且阻塞当前线程直到完成
            future.channel().closeFuture().sync();
        } finally {
            //关闭EventLoopGroup，释放所有的资源
            group.shutdownGracefully().sync();
        }
    }
}
