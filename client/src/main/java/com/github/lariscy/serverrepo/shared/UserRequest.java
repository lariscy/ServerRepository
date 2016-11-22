package com.github.lariscy.serverrepo.shared;

/**
 * @author Steven Lariscy
 */
public class UserRequest implements NettyObj {
    
    private UserRequestType requestType;

    public UserRequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(UserRequestType requestType) {
        this.requestType = requestType;
    }
    
}
