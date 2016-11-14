package com.bcbssc.serverrepo.client.controller;

import com.airhacks.afterburner.views.FXMLView;
import com.bcbssc.serverrepo.client.view.LoginFormView;
import com.bcbssc.serverrepo.client.view.ServerTreeView;
import com.bcbssc.serverrepo.client.view.UrlTreeView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
public class AppContainerController implements Initializable {
    
    private static final Logger log = LoggerFactory.getLogger(AppContainerController.class);

    @FXML
    private BorderPane borderPane;
    
    // references to all sub controllers
    @FXML
    private TopMenuController topMenuController;
    @FXML
    private LeftMenuController leftMenuController;
    @FXML
    private BottomInfoBarController bottomInfoBarController;
    
    private FXMLView serverTreeView;
    private FXMLView urlTreeView;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.debug("initializing");
        
        // List of child controllers loaded by FXMLLoader 
        // when the parent FXML (AppContainer.fxml) is loaded
        ChildController[] childControllers = new ChildController[] {
            topMenuController, leftMenuController, bottomInfoBarController
        };
        for (ChildController c : childControllers){
            c.setParentController(this);
        }
        
        this.loadLoginView();
    }
    
    public void loadLoginView(){
        this.loadViewCenter(new LoginFormView());
    }
    
    public void loadServerTreeView(){
        if (serverTreeView == null){
            serverTreeView = new ServerTreeView();
        }
        this.loadViewCenter(serverTreeView);
    }
    
    public void loadURLTreeView(){
        if (urlTreeView == null){
            urlTreeView = new UrlTreeView();
        }
        this.loadViewCenter(urlTreeView);
    }
    
    /**
     * Views loaded by this method must extend
     * com.airhacks.afterburner.views.FXMLView
     * 
     * The Controller / Presenter that the view references should also extend
     * com.bcbssc.serverrepo.client.controller.ChildController
     * 
     * @param fxmlView 
     */
    private void loadViewCenter(FXMLView fxmlView){
        ChildController childController = (ChildController) fxmlView.getPresenter();
        if (childController.getParentController() == null){
            childController.setParentController(this);
        }
        this.borderPane.setCenter(fxmlView.getView());
    }
    
}
