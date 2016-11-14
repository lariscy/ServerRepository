package com.bcbssc.serverrepo.client.controller;

import com.bcbssc.serverrepo.client.model.InfoBarStatus;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
public class BottomInfoBarController extends ChildController implements Initializable {
    
    private static final Logger log = LoggerFactory.getLogger(BottomInfoBarController.class);
    
    @FXML
    private ToolBar toolBar;
    @FXML
    private Label statusText;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.debug("initializing");
        
        statusText.setText(InfoBarStatus.DISCONNECTED.getText());
    }
    
}
