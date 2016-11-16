package com.github.lariscy.serverrepo.client.eventbus;

/**
 * @author Steven Lariscy
 */
public class LogoutEvent extends SimpleResponseEvent {

    public LogoutEvent(boolean success, String message) {
        super(success, message);
    }
    
    @Override
    public String toString() {
        return "LogoutEvent{" + "success=" + isSuccess() + ", message=" + getMessage() + '}';
    }
    
}
