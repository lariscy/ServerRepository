package com.bcbssc.serverrepo.client.eventbus;

/**
 * @author jw38
 */
public class LoginEvent implements ServerRepoEvent {
    
    private boolean success;
    private String message;

    public LoginEvent(boolean isSuccess, String message) {
        this.success = isSuccess;
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
        return "LoginEvent{" + "isSuccess=" + success + ", message=" + message + '}';
    }
    
}
