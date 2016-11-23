package com.github.lariscy.serverrepo.client.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven Lariscy
 */
public class ServerRepoClient {
    
    private static final Logger LOG = LoggerFactory.getLogger(ServerRepoClient.class);
    
    private String serverHost = "localhost";
    private int serverPort = 3026;
    private EventLoopGroup group;
    private Bootstrap bootstrap;
    private ChannelHandlerContext ctx;
    private ServerRepoClient instance;
    
    public ServerRepoClient(){
        instance = this;
        this.setupClient();
    }
    
    public ServerRepoClient(String serverHost, int serverPort){
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        
        this.setupClient();
    }
    
    private void setupClient(){
        group = new NioEventLoopGroup();

        LOG.debug("initializing network client");

        bootstrap = new Bootstrap();
        bootstrap.group(group)
            .channel(NioSocketChannel.class)
            .handler(new ChannelInitializer<SocketChannel>(){
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(
                        new ObjectEncoder(),
                        new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                        new LoginHandler(instance)
                    );
                }
            });
    }
    
    public boolean connect(){
        LOG.debug("connecting to {}:{}", serverHost, serverPort);
        ChannelFuture connectFuture = bootstrap.connect(serverHost, serverPort);
        connectFuture.awaitUninterruptibly();
        if (!connectFuture.isSuccess()){
            LOG.error("exception during client disconnect", connectFuture.cause());
        }
        return connectFuture.isSuccess();
    }
    
    public boolean disconnect(){
        LOG.debug("disconnecting");
        ChannelFuture disconnectFuture = ctx.disconnect();
        disconnectFuture.awaitUninterruptibly();
        if (!disconnectFuture.isSuccess()){
            LOG.error("exception during client disconnect", disconnectFuture.cause());
        }
        return disconnectFuture.isSuccess();
    }
    
    public void shutdownClient(){
        LOG.debug("shutting down any remaining client connections");
        group.shutdownGracefully();
    }
    
    public ChannelHandlerContext getChannelHandlerContext() {
        return ctx;
    }
    
    public void setChannelHandlerContext(ChannelHandlerContext ctx){
        this.ctx = ctx;
    }
    
}
