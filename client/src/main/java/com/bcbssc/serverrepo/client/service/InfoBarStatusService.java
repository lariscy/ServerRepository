package com.bcbssc.serverrepo.client.service;

import com.bcbssc.serverrepo.client.MainApp;
import com.bcbssc.serverrepo.client.eventbus.InfoBarStatusEvent;
import com.bcbssc.serverrepo.client.model.InfoBarStatus;
import com.bcbssc.serverrepo.client.model.UserRole;
import com.google.inject.Singleton;

/**
 * @author jw38
 */
@Singleton
public class InfoBarStatusService {
    
    public void updateStatus(InfoBarStatus status){
        MainApp.getEventBus().publishAsync(new InfoBarStatusEvent(status));
    }
    
    public void updateStatusRole(UserRole userRole){
        MainApp.getEventBus().publishAsync(new InfoBarStatusEvent(userRole));
    }
    
}
