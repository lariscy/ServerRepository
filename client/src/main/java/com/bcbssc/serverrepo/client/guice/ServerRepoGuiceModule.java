package com.bcbssc.serverrepo.client.guice;

import com.bcbssc.serverrepo.client.eventbus.ServerRepoEvent;
import com.bcbssc.serverrepo.client.service.*;
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
    }
    
    private MBassador getEventBusInstance(){
        MBassador<ServerRepoEvent> eventBus = new MBassador<>();
        return eventBus;
    }
    
}
