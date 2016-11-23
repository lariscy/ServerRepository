package com.github.lariscy.serverrepo.client.guice;

import com.github.lariscy.serverrepo.client.eventbus.ServerRepoEvent;
import com.github.lariscy.serverrepo.client.service.CenterNodeViewService;
import com.github.lariscy.serverrepo.client.service.InfoBarStatusService;
import com.google.inject.AbstractModule;
import net.engio.mbassy.bus.MBassador;

/**
 * @author Steven Lariscy
 */
public class ServerRepoGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MBassador.class).toInstance(this.getEventBusInstance());
        bind(InfoBarStatusService.class).asEagerSingleton();
        bind(CenterNodeViewService.class).asEagerSingleton();
    }
    
    private MBassador getEventBusInstance(){
        MBassador<ServerRepoEvent> eventBus = new MBassador<>();
        return eventBus;
    }
    
}
