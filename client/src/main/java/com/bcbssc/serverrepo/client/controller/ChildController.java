package com.bcbssc.serverrepo.client.controller;

/**
 * @author jw38
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
