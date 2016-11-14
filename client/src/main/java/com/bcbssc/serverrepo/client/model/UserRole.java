package com.bcbssc.serverrepo.client.model;

/**
 * @author jw38
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
