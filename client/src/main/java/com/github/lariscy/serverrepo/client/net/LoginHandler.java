package com.github.lariscy.serverrepo.client.net;

import com.github.lariscy.serverrepo.shared.net.response.LoginLogoutResponse;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven Lariscy
 */
public class LoginHandler extends ServerRepoClientHandler<LoginLogoutResponse> {
    
    private static final Logger LOG = LoggerFactory.getLogger(ServerRepoClientHandler.class);

    public LoginHandler(ServerRepoClient client) {
        super(client);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginLogoutResponse obj) throws Exception {
        //@TODO stubbed subclass
        
    }
    
}
