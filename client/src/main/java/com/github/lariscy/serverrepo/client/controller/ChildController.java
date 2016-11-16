package com.github.lariscy.serverrepo.client.controller;

/**
 * @author Steven Lariscy
 */
public class ChildController {
    
    private AppContainerController parentController;
            
    public final void setParentController(AppContainerController parentController){
        this.parentController = parentController;
    }
    
    public final AppContainerController getParentController(){
        return parentController;
    }
    
}
