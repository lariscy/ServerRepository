package com.bcbssc.serverrepo.client.eventbus;

import com.bcbssc.serverrepo.client.model.User;

/**
 * @author jw38
 */
public class LoginEvent extends SimpleResponseEvent {

    private User user;
    
    public LoginEvent(boolean success, User user, String message) {
        super(success, message);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public String toString() {
        return "LoginEvent{user=" + user + ", isSuccess=" + isSuccess() + ", message=" + getMessage() + '}';
    }
    
}
