package com.github.lariscy.serverrepo.client.service;

import com.github.lariscy.serverrepo.client.eventbus.LoginEvent;
import com.github.lariscy.serverrepo.client.eventbus.LogoutEvent;
import com.github.lariscy.serverrepo.client.model.User;
import com.github.lariscy.serverrepo.client.model.UserRole;
import com.google.inject.Singleton;
import javax.inject.Inject;
import net.engio.mbassy.bus.MBassador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven Lariscy
 */
@Singleton
public class LdapUserService implements UserService {
    
    private static final Logger LOG = LoggerFactory.getLogger(LdapUserService.class);

    private User user;
    
    public LdapUserService(){
        user = new User();
    }
    
    @Inject
    private MBassador eventBus;
    
    @Override
    public boolean login(String userName, String password) {
        LOG.debug("attempting to login as user [{}]", userName);
        //@TODO add real login logic
        
        if (userName.equals("admin") && password.equals("admin")){
            user.setUserName(userName);
            user.setIsLoggedIn(true);
            user.setUserRole(UserRole.ADMIN);
            eventBus.publishAsync(new LoginEvent(true, user, "login success"));
            return true;
        } else if (userName.equals("user") && password.equals("user")){
            user.setUserName(userName);
            user.setIsLoggedIn(true);
            user.setUserRole(UserRole.USER);
            eventBus.publishAsync(new LoginEvent(true, user, "login success"));
            return true;
        } else if (userName.equals("readonly") && password.equals("readonly")){
            user.setUserName(userName);
            user.setIsLoggedIn(true);
            user.setUserRole(UserRole.READONLY);
            eventBus.publishAsync(new LoginEvent(true, user, "login success"));
            return true;
        }
        
        eventBus.publishAsync(new LoginEvent(false, null, "Error: Login failed!"));
        return false;
    }

    @Override
    public boolean loginGuest() {
        //@TODO put some real logic here
        
        LOG.debug("logging in as guest");
        user.setUserName("anonymous");
        user.setIsLoggedIn(true);
        user.setUserRole(UserRole.READONLY);
        eventBus.publishAsync(new LoginEvent(true, user, "login success"));
        return true;
    }

    @Override
    public boolean logout() {
        //@TODO put some real logic here
        
        user.setIsLoggedIn(false);
        eventBus.publishAsync(new LogoutEvent(true, "logout success"));
        return true;
    }

    @Override
    public User getUser() {
        return user;
    }
    
}
