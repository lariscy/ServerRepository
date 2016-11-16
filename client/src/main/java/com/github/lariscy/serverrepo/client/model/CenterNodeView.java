package com.github.lariscy.serverrepo.client.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * @author jw38
 */
public class CenterNodeView {
    
    private BooleanProperty isLoginFormViewActive = new SimpleBooleanProperty(false);
    private BooleanProperty isServerTreeViewActive = new SimpleBooleanProperty(false);
    private BooleanProperty isUrlTreeViewActive = new SimpleBooleanProperty(false);

    public boolean isIsLoginFormViewActive(){
        return isLoginFormViewActive.getValue();
    }
    
    public void setIsLoginFormViewActive(boolean isActive){
        isLoginFormViewActive.setValue(isActive);
    }
    
    public BooleanProperty getIsLoginFormViewActiveProperty() {
        return isLoginFormViewActive;
    }
    
    public boolean isIsServerTreeViewActive(){
        return isServerTreeViewActive.getValue();
    }
    
    public void setIsServerTreeViewActive(boolean isActive){
        isServerTreeViewActive.setValue(isActive);
    }

    public BooleanProperty getIsServerTreeViewActiveProperty() {
        return isServerTreeViewActive;
    }
    
    public boolean isIsUrlTreeViewActive(){
        return isUrlTreeViewActive.getValue();
    }
    
    public void setIsUrlTreeViewActive(boolean isActive){
        isUrlTreeViewActive.setValue(isActive);
    }

    public BooleanProperty getIsUrlTreeViewActiveProperty() {
        return isUrlTreeViewActive;
    }
    
}
