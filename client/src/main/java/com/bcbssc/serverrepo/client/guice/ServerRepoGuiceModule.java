package com.bcbssc.serverrepo.client.guice;

import com.bcbssc.serverrepo.client.service.*;
import com.google.inject.AbstractModule;

/**
 * @author jw38
 */
public class ServerRepoGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserService.class).to(LdapUserService.class).asEagerSingleton();
        bind(InfoBarStatusService.class).asEagerSingleton();
    }
    
}
