package com.github.lariscy.serverrepo.client.eventbus;

/**
 * @author jw38
 */
public class SimpleResponseEvent implements ServerRepoEvent {
    
    private boolean success;
    private String message;

    public SimpleResponseEvent(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SimpleResponseEvent{" + "success=" + success + ", message=" + message + '}';
    }
    
}
