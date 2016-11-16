package com.github.lariscy.serverrepo.client.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * @author Steven Lariscy
 */
public class User {
    
    private String userName;
    private UserRole userRole;
    //private boolean isLoggedIn;
    private BooleanProperty isLoggedInProperty = new SimpleBooleanProperty(false);

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isIsLoggedIn() {
        return isLoggedInProperty.getValue();
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedInProperty.setValue(isLoggedIn);
    }
    
    public BooleanProperty getIsLoggedInProperty(){
        return isLoggedInProperty;
    }

    @Override
    public String toString() {
        return "User{" + "userName=" + userName + ", userRole=" + userRole + ", isLoggedIn=" + isLoggedInProperty.getValue() + '}';
    }
    
}
