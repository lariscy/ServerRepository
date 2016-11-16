package com.bcbssc.serverrepo.client.guice;

import com.bcbssc.serverrepo.client.eventbus.ServerRepoEvent;
import com.bcbssc.serverrepo.client.service.CenterNodeViewService;
import com.bcbssc.serverrepo.client.service.InfoBarStatusService;
import com.bcbssc.serverrepo.client.service.LdapUserService;
import com.bcbssc.serverrepo.client.service.UserService;
import com.google.inject.AbstractModule;
import net.engio.mbassy.bus.MBassador;

/**
 * @author jw38
 */
public class ServerRepoGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MBassador.class).toInstance(this.getEventBusInstance());
        bind(UserService.class).to(LdapUserService.class).asEagerSingleton();
        bind(InfoBarStatusService.class).asEagerSingleton();
        bind(CenterNodeViewService.class).asEagerSingleton();
    }
    
    private MBassador getEventBusInstance(){
        MBassador<ServerRepoEvent> eventBus = new MBassador<>();
        return eventBus;
    }
    
}
