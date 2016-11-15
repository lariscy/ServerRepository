package com.bcbssc.serverrepo.client.controller;

import com.bcbssc.serverrepo.client.eventbus.InfoBarStatusEvent;
import com.bcbssc.serverrepo.client.model.InfoBarStatus;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javax.inject.Inject;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.listener.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
public class BottomInfoBarController extends ChildController implements Initializable {
    
    private static final Logger LOG = LoggerFactory.getLogger(BottomInfoBarController.class);
    
    @FXML
    private ToolBar toolBar;
    @FXML
    private Label statusText;
    @FXML
    private Label roleTxt;
    
    @Inject
    private MBassador eventBus;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOG.debug("initializing");
        
        statusText.setText(InfoBarStatus.DISCONNECTED.getText());
        
        eventBus.subscribe(this);
    }
    
    @Handler
    private void ebUpdateStatusEvent(InfoBarStatusEvent infoBarStatusEvent){
        Platform.runLater(() -> {
            if (infoBarStatusEvent.getStatus()!=null)
                statusText.setText(infoBarStatusEvent.getStatus().getText());
            if (infoBarStatusEvent.getUserRole()!=null)
                roleTxt.setText(infoBarStatusEvent.getUserRole().getText());
        });
    }
    
}
