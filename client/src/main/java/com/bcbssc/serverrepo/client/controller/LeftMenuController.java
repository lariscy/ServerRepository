package com.bcbssc.serverrepo.client.controller;

import com.bcbssc.serverrepo.client.eventbus.LogoutEvent;
import com.bcbssc.serverrepo.client.util.FontAwesome;
import com.bcbssc.serverrepo.client.util.ToolTipUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javax.inject.Inject;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.listener.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
public class LeftMenuController extends ChildController implements Initializable {

    private static final Logger LOG = LoggerFactory.getLogger(LeftMenuController.class);
    
    @FXML
    private VBox parentVBox;
    @FXML
    private ToggleButton btnServerList;
    @FXML
    private ToggleButton btnUrlList;
    
    @Inject
    private MBassador eventBus;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOG.debug("initializing");
        
        btnServerList.setText(FontAwesome.getText(FontAwesome.Glyph.SERVER));
        btnUrlList.setText(FontAwesome.getText(FontAwesome.Glyph.GLOBE));
        
        this.setupToolTips();
        
        eventBus.subscribe(this);
    }
    
    private void setupToolTips(){
        Tooltip serverListButtonTT = new Tooltip("Servers");
        ToolTipUtil.hackTooltipStartTiming(serverListButtonTT);
        btnServerList.setTooltip(serverListButtonTT);
        
        Tooltip urlListButtonTT = new Tooltip("URLs");
        ToolTipUtil.hackTooltipStartTiming(urlListButtonTT);
        btnUrlList.setTooltip(urlListButtonTT);
    }
    
    @FXML
    public void gotoServerList(){
        if (!btnServerList.isSelected()){
            btnServerList.setSelected(true);
            return;
        }
        if (!this.getParentController().loadServerTreeView())
            btnServerList.setSelected(false);
    }
    
    @FXML
    public void gotoUrlList(){
        if (!btnUrlList.isSelected()){
            btnUrlList.setSelected(true);
            return;
        }
        if (!this.getParentController().loadURLTreeView())
            btnUrlList.setSelected(false);
    }
    
    public void setServerListToggleActive(){
        btnServerList.setSelected(true);
        btnUrlList.setSelected(false);
    }
    
    public void setUrlListToggleActive(){
        btnUrlList.setSelected(true);
        btnServerList.setSelected(false);
    }
    
    @Handler
    private void ebLogoutEvent(LogoutEvent logoutEvent){
        if (logoutEvent.isSuccess()){
            Platform.runLater(() -> {
                btnUrlList.setSelected(false);
                btnServerList.setSelected(false);
            });
        }
    }
    
}
