package com.github.lariscy.serverrepo.client.service;

import com.github.lariscy.serverrepo.client.model.User;

/**
 * @author Steven Lariscy
 */
public interface UserService {
    
    boolean login(String userName, String password);
    boolean loginGuest();
    boolean logout();
    User getUser();
    
}
