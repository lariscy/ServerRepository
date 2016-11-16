package com.bcbssc.serverrepo.client.service;

import com.bcbssc.serverrepo.client.model.User;

/**
 * @author jw38
 */
public interface UserService {
    
    boolean login(String userName, String password);
    boolean loginGuest();
    boolean logout();
    User getUser();
    
}
