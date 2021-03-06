package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

import javax.annotation.concurrent.Immutable;
import java.net.InetSocketAddress;

/**
 * @author dreamyao
 * @title
 * @date 2018/3/8 下午10:25
 * @since 1.0.0
 */
@Immutable
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        /*if (args.length != 1) {
            System.out.println("Usage: " + EchoServer.class.getSimpleName() + "<port>");
            return;
        }
        // 设置端口值
        int port = Integer.parseInt(args[0]);*/
        // 调用服务器的start()
        System.out.println("Usage: " + EchoServer.class.getSimpleName() + "<port>");
        new EchoServer(8888).start();
    }

    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            // 指定所使用的NIO传输Channel
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // EchoServerHandler被标注为@Shareable，所以我们可以总是使用同样的实列
                            ByteBuf buf = Unpooled.copiedBuffer("!".getBytes());
                            ch.pipeline()
                                    /*.addLast(new DelimiterBasedFrameDecoder(1024))
                                    .addLast(new StringDecoder())*/
                                    .addLast(new DelimiterBasedFrameDecoder(1024,buf))
                                    .addLast("serverHandler", serverHandler)
                                    .addLast("serverHandlerTwo",new EchoServerHandlerTwo());
                        }
                    });
            // 异步地绑定服务器调用sync()方法阻塞等待直到绑定完成
            ChannelFuture future = b.bind().sync();
            // 获取Channel的CloseFuture，并且阻塞当前线程直到它完成
            future.channel().closeFuture().sync();
        } finally {
            // 关闭EventLoopGroup释放所有的资源
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        }
    }
}
