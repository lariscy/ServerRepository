package com.bcbssc.serverrepo.client.controller;

import com.bcbssc.serverrepo.client.MainApp;
import com.bcbssc.serverrepo.client.eventbus.LoginEvent;
import com.bcbssc.serverrepo.client.service.LdapUserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.inject.Inject;
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
    private LdapUserService userService;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOG.debug("initializing");
        
        lblLoginTitle.setText(MainApp.getAppProps().getProp("application.name", "app.name"));
        
        Platform.runLater(() -> {
            txtUsername.requestFocus();
        });
        
        MainApp.getEventBus().subscribe(this);
    }
    
    @FXML
    private void handleLogin(){
        //@TODO change to real implementation
        String userTxt = txtUsername.getText().trim();
        String userPass = txtPassword.getText().trim();
        //LOG.debug("handleLogin() clicked with user ["+userTxt+"] and pass ["+userPass+"]");
        userService.login(userTxt, userPass);
    }
    
    @Handler
    private void ebLoginEvent(LoginEvent loginEvent){
        LOG.debug("LoginEvent received - {}", loginEvent);
        if (loginEvent.isSuccess()){
            LOG.debug("login successful");
            Platform.runLater(() -> 
                this.getParentController().loadServerTreeView());
        } else {
            LOG.debug("login failed");
            Platform.runLater(() -> {
                txtPassword.setText("");
                lblErrorMessage.setText(loginEvent.getMessage());
            });
        }
    }
    
}
