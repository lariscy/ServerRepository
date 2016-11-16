package com.github.lariscy.serverrepo.client.net;

import com.github.lariscy.serverrepo.client.concurrent.ServerTreeManager;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
public class ServerRepoClient {
    
    private static final Logger LOG = LoggerFactory.getLogger(ServerRepoClient.class);
    
    private String serverHost = "127.0.0.1";
    private int serverPort = 3026;
    private EventLoopGroup group;
    
    public ServerRepoClient(){ }
    
    public ServerRepoClient(String serverHost, int serverPort){
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }
    
    public void setupClient(ServerTreeManager serverTreeManager){
        group = new NioEventLoopGroup();

        LOG.debug("initializing network client");

        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>(){
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(
                            new ObjectEncoder(),
                            new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                            new ServerRepoClientHandler(serverTreeManager)
                        );
                    }
                });

        LOG.debug("connecting to {}:{}", serverHost, serverPort);
        ChannelFuture channelFuture = b.connect(serverHost, serverPort);
        channelFuture.addListener((FutureListener<Void>) (Future<Void> f) -> {
            if (!f.isSuccess()) {
                LOG.error("exception during client connection", f.cause());
            } else if (f.isSuccess()){
                LOG.debug("client connected successfully");
            }
        });
    }
    
    public void shutdownClient(){
        group.shutdownGracefully();
    }
    
}
