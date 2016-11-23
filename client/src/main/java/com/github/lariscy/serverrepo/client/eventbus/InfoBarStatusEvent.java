package com.github.lariscy.serverrepo.client.eventbus;

import com.github.lariscy.serverrepo.client.model.InfoBarStatus;
import com.github.lariscy.serverrepo.shared.net.model.UserRole;

/**
 * @author Steven Lariscy
 */
public class InfoBarStatusEvent implements ServerRepoEvent {
    
    private InfoBarStatus status;
    private UserRole userRole;

    public InfoBarStatusEvent(InfoBarStatus status) {
        this.status = status;
    }
    
    public InfoBarStatusEvent(UserRole userRole) {
        this.userRole = userRole;
    }

    public InfoBarStatus getStatus() {
        return status;
    }

    public void setStatus(InfoBarStatus status) {
        this.status = status;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "InfoBarStatusEvent{" + "status=" + status + ", userRole=" + userRole + '}';
    }
    
}
