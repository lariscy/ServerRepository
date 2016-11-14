package com.bcbssc.serverrepo.client.controller;

import com.bcbssc.serverrepo.client.event.AppCloseEvent;
import com.bcbssc.serverrepo.client.event.AppHideEvent;
import com.bcbssc.serverrepo.client.service.LdapUserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
public class TopMenuController extends ChildController implements Initializable {
    
    private static final Logger log = LoggerFactory.getLogger(TopMenuController.class);

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu fileMenu;
    @FXML
    private MenuItem logoutMenuItem;
    @FXML
    private MenuItem exitMenuItem;
    @FXML
    private Menu optionsMenu;
    @FXML
    private MenuItem expandAllMenuItem;
    @FXML
    private MenuItem shrinkAllMenuItem;
    @FXML
    private MenuItem settingsMenuItem;
    @FXML
    private Menu themeMenu;
    @FXML
    private MenuItem themeDefaultMenuItem;
    @FXML
    private Menu helpMenu;
    @FXML
    private MenuItem aboutMenuItem;
    
    @Inject
    private LdapUserService userService;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.debug("initializing");
        
        exitMenuItem.setOnAction(
            (java.awt.SystemTray.isSupported() ? new AppHideEvent() : new AppCloseEvent())
        );
    }
    
    @FXML
    private void handleLogout(){
        if (userService.logout()){
            log.debug("user logged out successfully");
            this.getParentController().loadLoginView();
        }
    }
    
}
