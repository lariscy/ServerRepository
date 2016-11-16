package com.bcbssc.serverrepo.client.service;

import com.bcbssc.serverrepo.client.model.CenterNodeView;

/**
 * @author jw38
 */
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
