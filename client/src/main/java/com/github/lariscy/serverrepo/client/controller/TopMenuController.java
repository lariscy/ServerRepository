package com.github.lariscy.serverrepo.client.controller;

import com.github.lariscy.serverrepo.client.event.AppCloseEvent;
import com.github.lariscy.serverrepo.client.event.AppHideEvent;
import com.github.lariscy.serverrepo.client.eventbus.LogoutEvent;
import com.github.lariscy.serverrepo.client.model.InfoBarStatus;
import com.github.lariscy.serverrepo.shared.net.model.UserRole;
import com.github.lariscy.serverrepo.client.service.CenterNodeViewService;
import com.github.lariscy.serverrepo.client.service.InfoBarStatusService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
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
 * @author Steven Lariscy
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
    private InfoBarStatusService infoBarStatusService;
    @Inject
    private CenterNodeViewService centerNodeViewService;
    @Inject
    private MBassador eventBus;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOG.debug("initializing");
        
        this.setupBindings();
        exitMenuItem.setOnAction(
            (java.awt.SystemTray.isSupported() ? new AppHideEvent() : new AppCloseEvent())
        );
        
        eventBus.subscribe(this);
    }
    
    private void setupBindings(){
        // only enabled Logout menu item if user is logged in
//        logoutMenuItem.disableProperty().bind(new BooleanBinding(){
//            {
//                super.bind(userService.getUser().getIsLoggedInProperty());
//            }
//            @Override
//            protected boolean computeValue() {
//                return !userService.getUser().isIsLoggedIn();
//            }
//        });
        
        BooleanBinding expandShrinkBinding = new BooleanBinding(){
            {
                super.bind(
                    centerNodeViewService.getCenterNodeView().getIsServerTreeViewActiveProperty(),
                    centerNodeViewService.getCenterNodeView().getIsUrlTreeViewActiveProperty()
                );
            }
            @Override
            protected boolean computeValue() {
                return (
                    !centerNodeViewService.getCenterNodeView().isIsServerTreeViewActive() &&
                    !centerNodeViewService.getCenterNodeView().isIsUrlTreeViewActive()
                );
            }
        };
        // only enable Expand All and Shrink All menu items if those views
        // are active
        expandAllMenuItem.disableProperty().bind(expandShrinkBinding);
        shrinkAllMenuItem.disableProperty().bind(expandShrinkBinding);
    }
    
    @FXML
    private void handleLogout(){
        //userService.logout();
    }
    
    @Handler
    private void ebLogoutEvent(LogoutEvent logoutEvent){
        LOG.debug("LogoutEvent received - {}", logoutEvent);
        if (logoutEvent.isSuccess()){
            LOG.debug("logout successful ");
            Platform.runLater(getParentController()::loadLoginView);
            infoBarStatusService.updateStatus(InfoBarStatus.DISCONNECTED);
            infoBarStatusService.updateStatusRole(UserRole.NONE);
        } else {
            LOG.debug("logout failed");
        }
    }
    
}
