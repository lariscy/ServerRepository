package com.bcbssc.serverrepo.client.controller;

import com.bcbssc.serverrepo.client.MainApp;
import com.bcbssc.serverrepo.client.model.User;
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
    
    private final String FXML_LOGINFORM = "/com/bcbssc/serverrepo/client/view/LoginForm.fxml";
    private final String FXML_SERVERTREE = "/com/bcbssc/serverrepo/client/view/ServerTree.fxml";
    private final String FXML_URLTREE = "/com/bcbssc/serverrepo/client/view/UrlTree.fxml";
    
    private Parent serverTreeView;
    private ServerTreeController serverTreeController;
    private Parent urlTreeView;
    private UrlTreeController urlTreeController;
    
    @Inject
    private UserService userService;
    
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
        FXMLLoader loader = getLoaderForFxml(FXML_SERVERTREE);
        serverTreeView = getNodeFromLoader(loader);
        serverTreeController = (ServerTreeController) getControllerFromLoader(loader);
        
        loader = getLoaderForFxml(FXML_URLTREE);
        urlTreeView = getNodeFromLoader(loader);
        urlTreeController = (UrlTreeController) getControllerFromLoader(loader);
    }
    
    public void loadLoginView(){
        this.loadNodeCenter(loginForm);
        loginFormController.setLoginFocus();
    }
    
    public boolean loadServerTreeView(){
        User user = userService.getUser();
        if (user!=null && user.isIsLoggedIn()){
            this.loadNodeCenter(serverTreeView);
            leftMenuController.setServerListToggleActive();
            return true;
        }
        return false;
    }
    
    public boolean loadURLTreeView(){
        User user = userService.getUser();
        if (user!=null && user.isIsLoggedIn()){
            this.loadNodeCenter(urlTreeView);
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
        ((ChildController) fxmlLoader.getController()).setParentController(this);
        return (ChildController) fxmlLoader.getController();
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
