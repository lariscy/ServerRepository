package com.bcbssc.serverrepo.client.controller;

import com.bcbssc.serverrepo.client.FilePath;
import com.bcbssc.serverrepo.client.MainApp;
import com.bcbssc.serverrepo.client.model.CenterNodeView;
import com.bcbssc.serverrepo.client.model.User;
import com.bcbssc.serverrepo.client.service.CenterNodeViewService;
import com.bcbssc.serverrepo.client.service.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
public class AppContainerController implements Initializable {
    
    private static final Logger LOG = LoggerFactory.getLogger(AppContainerController.class);

    @FXML
    private BorderPane borderPane;
    @FXML
    private TopMenuController topMenuController;
    @FXML
    private LeftMenuController leftMenuController;
    @FXML
    private BottomInfoBarController bottomInfoBarController;
    @FXML
    private VBox loginForm;
    @FXML
    private LoginFormController loginFormController;
    
    private Parent serverTreeView;
    private ServerTreeController serverTreeController;
    private Parent urlTreeView;
    private UrlTreeController urlTreeController;
    
    @Inject
    private UserService userService;
    @Inject
    private CenterNodeViewService centerNodeViewService;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOG.debug("initializing");
        
        topMenuController.setParentController(this);
        leftMenuController.setParentController(this);
        bottomInfoBarController.setParentController(this);
        loginFormController.setParentController(this);
        
        this.initOtherViews();
    }
    
    private void initOtherViews(){
        FXMLLoader loader = getLoaderForFxml(FilePath.FXML_SERVERTREE);
        serverTreeView = getNodeFromLoader(loader);
        serverTreeController = (ServerTreeController) getControllerFromLoader(loader);
        
        loader = getLoaderForFxml(FilePath.FXML_URLTREE);
        urlTreeView = getNodeFromLoader(loader);
        urlTreeController = (UrlTreeController) getControllerFromLoader(loader);
    }
    
    public void loadLoginView(){
        this.loadNodeCenter(loginForm);
        centerNodeViewService.setLoginViewActive();
        loginFormController.setLoginFocus();
    }
    
    public boolean loadServerTreeView(){
        User user = userService.getUser();
        if (user!=null && user.isIsLoggedIn()){
            this.loadNodeCenter(serverTreeView);
            centerNodeViewService.setServerTreeViewActive();
            leftMenuController.setServerListToggleActive();
            return true;
        }
        return false;
    }
    
    public boolean loadURLTreeView(){
        User user = userService.getUser();
        if (user!=null && user.isIsLoggedIn()){
            this.loadNodeCenter(urlTreeView);
            centerNodeViewService.setUrlTreeViewActive();
            leftMenuController.setUrlListToggleActive();
            return true;
        }
        return false;
    }
    
    private void loadNodeCenter(Node node){
        this.borderPane.setCenter(node);
    }
    
    private FXMLLoader getLoaderForFxml(String fxmlPath){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(fxmlPath));
        fxmlLoader.setControllerFactory(MainApp.getInjector()::getInstance);
        return fxmlLoader;
    }
    
    private ChildController getControllerFromLoader(FXMLLoader fxmlLoader){
        ChildController childController = (ChildController) fxmlLoader.getController();
        childController.setParentController(this);
        return childController;
    }
    
    private Parent getNodeFromLoader(FXMLLoader fxmlLoader){
        try {
            return fxmlLoader.load();
        } catch (IOException ex) {
            LOG.error("error loading FXML [{}]", fxmlLoader.getLocation(), ex);
        }
        return null;
    }
    
}
