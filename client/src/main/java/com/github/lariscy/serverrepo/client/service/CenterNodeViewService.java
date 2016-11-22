package com.github.lariscy.serverrepo.client.service;

import com.github.lariscy.serverrepo.client.model.CenterNodeView;
import com.google.inject.Singleton;

/**
 * @author Steven Lariscy
 */
@Singleton
public class CenterNodeViewService {
    
    private CenterNodeView centerNodeView = new CenterNodeView();
    
    public void setLoginViewActive(){
        centerNodeView.setIsLoginFormViewActive(true);
        centerNodeView.setIsServerTreeViewActive(false);
        centerNodeView.setIsUrlTreeViewActive(false);
    }
    
    public void setServerTreeViewActive(){
        centerNodeView.setIsLoginFormViewActive(false);
        centerNodeView.setIsServerTreeViewActive(true);
        centerNodeView.setIsUrlTreeViewActive(false);
    }
    
    public void setUrlTreeViewActive(){
        centerNodeView.setIsLoginFormViewActive(false);
        centerNodeView.setIsServerTreeViewActive(false);
        centerNodeView.setIsUrlTreeViewActive(true);
    }
    
    public CenterNodeView getCenterNodeView(){
        return centerNodeView;
    }
    
}
