package com.bcbssc.serverrepo.client.controller;

import com.bcbssc.serverrepo.client.event.AppCloseEvent;
import com.bcbssc.serverrepo.client.event.AppHideEvent;
import com.bcbssc.serverrepo.client.eventbus.LogoutEvent;
import com.bcbssc.serverrepo.client.model.InfoBarStatus;
import com.bcbssc.serverrepo.client.model.UserRole;
import com.bcbssc.serverrepo.client.service.InfoBarStatusService;
import com.bcbssc.serverrepo.client.service.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javax.inject.Inject;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.listener.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
public class TopMenuController extends ChildController implements Initializable {
    
    private static final Logger LOG = LoggerFactory.getLogger(TopMenuController.class);

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
    private UserService userService;
    @Inject
    private InfoBarStatusService infoBarStatusService;
    @Inject
    private MBassador eventBus;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOG.debug("initializing");
        
        exitMenuItem.setOnAction(
            (java.awt.SystemTray.isSupported() ? new AppHideEvent() : new AppCloseEvent())
        );
        
        eventBus.subscribe(this);
    }
    
    @FXML
    private void handleLogout(){
        userService.logout();
    }
    
    @Handler
    private void ebLogoutEvent(LogoutEvent logoutEvent){
        LOG.debug("LogoutEvent received - {}", logoutEvent);
        if (logoutEvent.isSuccess()){
            LOG.debug("logout successful ");
//            Platform.runLater(() ->
//                this.getParentController().loadLoginView());
            Platform.runLater(getParentController()::loadLoginView);
            infoBarStatusService.updateStatus(InfoBarStatus.DISCONNECTED);
            infoBarStatusService.updateStatusRole(UserRole.NONE);
        } else {
            LOG.debug("logout failed");
        }
    }
    
}
