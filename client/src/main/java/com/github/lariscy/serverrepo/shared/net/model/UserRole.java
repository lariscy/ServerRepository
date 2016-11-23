package com.github.lariscy.serverrepo.shared.net.model;

/**
 * @author Steven Lariscy
 */
public enum UserRole {
    
    USER("User"),
    ADMIN("Admin"),
    READONLY("Read-Only"),
    NONE("");
    
    private final String ROLE_TEXT;
    
    private UserRole(String roleText){
        this.ROLE_TEXT = roleText;
    }

    public String getText(){
        return ROLE_TEXT;
    }
    
}
