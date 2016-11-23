package com.github.lariscy.serverrepo.client.net;

import com.github.lariscy.serverrepo.shared.net.NettyObj;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javax.inject.Inject;
import net.engio.mbassy.bus.MBassador;

/**
 * @author Steven Lariscy
 */
public abstract class ServerRepoClientHandler<T extends NettyObj> extends SimpleChannelInboundHandler<T> {
    
    // protected so subclasses can read directly
    protected ServerRepoClient client;
    
    @Inject
    private MBassador eventBus;

    public ServerRepoClientHandler(ServerRepoClient client) {
        this.client = client;
    }

    @Override
    protected abstract void channelRead0(ChannelHandlerContext ctx, T obj) throws Exception;
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        client.setChannelHandlerContext(ctx);
    }
    
}
