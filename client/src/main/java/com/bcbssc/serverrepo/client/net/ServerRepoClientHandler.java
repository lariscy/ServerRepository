package com.bcbssc.serverrepo.client.net;

import com.bcbssc.serverrepo.client.concurrent.ServerTreeManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
public class ServerRepoClientHandler extends ChannelInboundHandlerAdapter {
    
    private static final Logger log = LoggerFactory.getLogger(ServerRepoClientHandler.class);

    private ServerTreeManager serverTreeManager;
    
    public ServerRepoClientHandler(ServerTreeManager serverTreeManager){
        this.serverTreeManager = serverTreeManager;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        log.error("exception caught within ServerRepoClientHandler", cause);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx){
        log.debug("client channel writability has changed");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        log.debug("client channel read complete");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        if (msg instanceof NettyObj){
            log.debug("instance of NettyObj received");
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        log.debug("client channel is now inactive");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        log.debug("client channel is now active");
    }
    
}
