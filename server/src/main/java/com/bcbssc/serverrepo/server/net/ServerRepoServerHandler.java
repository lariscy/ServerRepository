package com.bcbssc.serverrepo.server.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
public class ServerRepoServerHandler extends ChannelInboundHandlerAdapter {
    
    private static final Logger log = LoggerFactory.getLogger(ServerRepoServerHandler.class);

    public ServerRepoServerHandler() {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        log.error("exception caught within ServerRepoServerHandler", cause);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx){
        log.debug("server channel writability has changed");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        log.debug("server channel read complete");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        log.debug("server channel read");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        log.debug("server channel inactive");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        log.debug("server channel active");
    }
    
}
