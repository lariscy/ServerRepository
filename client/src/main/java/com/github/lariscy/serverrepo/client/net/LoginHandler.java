package com.github.lariscy.serverrepo.client.net;

import com.github.lariscy.serverrepo.shared.UserRequest;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven Lariscy
 */
public class LoginHandler extends ServerRepoClientHandler<UserRequest> {
    
    private static final Logger LOG = LoggerFactory.getLogger(ServerRepoClientHandler.class);

    public LoginHandler(ServerRepoClient client) {
        super(client);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UserRequest obj) throws Exception {
        //@TODO stubbed subclass
        
        switch(obj.getRequestType()){
            case LOGIN:
                break;
            case LOGOUT:
                break;
            default:
                LOG.error("unexpected UserRequestType received");
        }
    }
    
}
