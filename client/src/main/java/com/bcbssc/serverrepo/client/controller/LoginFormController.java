package com.bcbssc.serverrepo.client.controller;

import com.bcbssc.serverrepo.client.MainApp;
import com.bcbssc.serverrepo.client.eventbus.LoginEvent;
import com.bcbssc.serverrepo.client.model.InfoBarStatus;
import com.bcbssc.serverrepo.client.model.UserRole;
import com.bcbssc.serverrepo.client.service.InfoBarStatusService;
import com.bcbssc.serverrepo.client.service.UserService;
import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.engio.mbassy.listener.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
public class LoginFormController extends ChildController implements Initializable {
    
    private static final Logger LOG = LoggerFactory.getLogger(LoginFormController.class);

//    @FXML
//    private Button btnLogin; // may not need reference to this
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblLoginTitle;
    @FXML
    private Label lblErrorMessage;
    
    @Inject
    private UserService userService;
    @Inject
    private InfoBarStatusService infoBarStatusService;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOG.debug("initializing");
        
        lblLoginTitle.setText(MainApp.getAppProps().getProp("application.name", "app.name"));
        
        this.setLoginFocus();
        
        MainApp.getEventBus().subscribe(this);
    }
    
    public void setLoginFocus(){
        Platform.runLater(() ->
            txtUsername.requestFocus());
    }
    
    @FXML
    private void handleLogin(){
        infoBarStatusService.updateStatus(InfoBarStatus.CONNECTING);
        //LOG.debug("handleLogin() clicked with user [{}] and pass [{}]", txtUsername.getText(), txtPassword.getText());
        userService.login(txtUsername.getText(), txtPassword.getText());
    }
    
    @Handler
    private void ebLoginEvent(LoginEvent loginEvent){
        LOG.debug("LoginEvent received - {}", loginEvent);
        if (loginEvent.isSuccess()){
            LOG.debug("login successful");
            Platform.runLater(() -> {
                txtUsername.setText("");
                txtPassword.setText("");
                lblErrorMessage.setText("");
                txtUsername.requestFocus();
                this.getParentController().loadServerTreeView();
            });
            infoBarStatusService.updateStatus(InfoBarStatus.CONNECTED);
            infoBarStatusService.updateStatusRole(loginEvent.getUser().getUserRole());
        } else {
            LOG.debug("login failed");
            Platform.runLater(() -> {
                txtPassword.setText("");
                lblErrorMessage.setText(loginEvent.getMessage());
            });
            infoBarStatusService.updateStatus(InfoBarStatus.DISCONNECTED);
            infoBarStatusService.updateStatusRole(UserRole.NONE);
        }
    }
    
}
