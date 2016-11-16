package com.github.lariscy.serverrepo.client.model;

/**
 * @author jw38
 */
public enum InfoBarStatus {
    
    CONNECTED("Connected"),
    DISCONNECTED("Disconnected"),
    CONNECTING("Connecting"),
    LOADING("Loading"),
    ERROR("Error");
    
    private final String STATUS_TEXT;
    
    private InfoBarStatus(String statusText){
        this.STATUS_TEXT = statusText;
    }
    
    public String getText(){
        return STATUS_TEXT;
    }
    
}
