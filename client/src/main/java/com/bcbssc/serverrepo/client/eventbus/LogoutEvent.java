package com.bcbssc.serverrepo.client.eventbus;

/**
 * @author jw38
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
