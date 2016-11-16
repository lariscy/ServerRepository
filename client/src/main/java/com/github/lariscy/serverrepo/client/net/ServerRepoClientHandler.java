package com.github.lariscy.serverrepo.client.net;

import com.github.lariscy.serverrepo.client.concurrent.ServerTreeManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven Lariscy
 */
public class ServerRepoClientHandler extends ChannelInboundHandlerAdapter {
    
    private static final Logger LOG = LoggerFactory.getLogger(ServerRepoClientHandler.class);

    private ServerTreeManager serverTreeManager;
    
    public ServerRepoClientHandler(ServerTreeManager serverTreeManager){
        this.serverTreeManager = serverTreeManager;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        LOG.error("exception caught within ServerRepoClientHandler", cause);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx){
        LOG.debug("client channel writability has changed");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        LOG.debug("client channel read complete");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        if (msg instanceof NettyObj){
            LOG.debug("instance of NettyObj received");
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        LOG.debug("client channel is now inactive");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        LOG.debug("client channel is now active");
    }
    
}
