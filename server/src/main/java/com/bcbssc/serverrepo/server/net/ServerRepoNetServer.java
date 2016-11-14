package com.bcbssc.serverrepo.server.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
public class ServerRepoNetServer {
    
    private static final Logger log = LoggerFactory.getLogger(ServerRepoNetServer.class);
    
    private int serverPort = 8383;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    
    public ServerRepoNetServer(){ }
    
    public ServerRepoNetServer(int serverPort){
        this.serverPort = serverPort;
    }
    
    public static void main(String[] args) {
        new ServerRepoNetServer().setupServer();
    }
    
    public void setupServer(){
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        
        log.debug("initializing network server");
        
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
         .channel(NioServerSocketChannel.class)
         .handler(new LoggingHandler(LogLevel.INFO))
         .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline p = ch.pipeline();
                p.addLast(
                    new ObjectEncoder(),
                    new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                    new ServerRepoServerHandler());
            }
         });

        // Bind and start to accept incoming connections.
        ChannelFuture channelFuture = b.bind(serverPort);
        channelFuture.addListener((FutureListener<Void>) (Future<Void> f) -> {
            if (!f.isSuccess()) {
                log.error("exception during server port bind", f.cause());
            } else if (f.isSuccess()){
                log.info("server port bind successful");
            }
        });
    }
    
    public void shutdownServer(){
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
    
}
