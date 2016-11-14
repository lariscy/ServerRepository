package com.bcbssc.serverrepo.client.service;

import com.bcbssc.serverrepo.client.MainApp;
import com.bcbssc.serverrepo.client.eventbus.LoginEvent;
import com.bcbssc.serverrepo.client.model.User;
import com.bcbssc.serverrepo.client.model.UserRole;

/**
 * @author jw38
 */
public class LdapUserService implements UserService {

    private User user;
    
    public LdapUserService(){
        
    }
    
    @Override
    public boolean login(String userName, String password) {
        //@TODO add login
        
        if (userName.equals("admin") && password.equals("admin")){
            user = new User();
            user.setUserName(userName);
            user.setIsLoggedIn(true);
            user.setUserRole(UserRole.ADMIN);
            MainApp.getEventBus().publishAsync(new LoginEvent(true, "login success"));
            return true;
        }
        MainApp.getEventBus().publishAsync(new LoginEvent(false, "login failed!"));
        return false;
    }

    @Override
    public boolean logout() {
        //@TODO add logout logic
        
        if (user!=null){
            user.setIsLoggedIn(false);
        }
        return true;
    }

    @Override
    public User getUser() {
        return user;
    }
    
}
