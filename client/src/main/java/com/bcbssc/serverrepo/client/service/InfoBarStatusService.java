package com.bcbssc.serverrepo.client.service;

import com.bcbssc.serverrepo.client.eventbus.InfoBarStatusEvent;
import com.bcbssc.serverrepo.client.model.InfoBarStatus;
import com.bcbssc.serverrepo.client.model.UserRole;
import com.google.inject.Singleton;
import javax.inject.Inject;
import net.engio.mbassy.bus.MBassador;

/**
 * @author jw38
 */
@Singleton
public class InfoBarStatusService {
    
    @Inject
    private MBassador eventBus;
    
    public void updateStatus(InfoBarStatus status){
        eventBus.publishAsync(new InfoBarStatusEvent(status));
    }
    
    public void updateStatusRole(UserRole userRole){
        eventBus.publishAsync(new InfoBarStatusEvent(userRole));
    }
    
}
