package com.bcbssc.serverrepo.client.controller;

import com.bcbssc.serverrepo.client.util.FontAwesome;
import com.bcbssc.serverrepo.client.util.ToolTipUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
public class LeftMenuController extends ChildController implements Initializable {

    private static final Logger log = LoggerFactory.getLogger(LeftMenuController.class);
    
    @FXML
    private VBox parentVBox;
    @FXML
    private ToggleButton btnServerList;
    @FXML
    private ToggleButton btnUrlList;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.debug("initializing");
        
        btnServerList.setText(FontAwesome.SERVER);
        btnUrlList.setText(FontAwesome.GLOBE);
        
        this.setupToolTips();
    }
    
    private void setupToolTips(){
        Tooltip serverListButtonTT = new Tooltip("Server List");
        ToolTipUtil.hackTooltipStartTiming(serverListButtonTT);
        btnServerList.setTooltip(serverListButtonTT);
        
        Tooltip urlListButtonTT = new Tooltip("URL List");
        ToolTipUtil.hackTooltipStartTiming(urlListButtonTT);
        btnUrlList.setTooltip(urlListButtonTT);
    }
    
    @FXML
    public void gotoServerList(){
        if (!btnServerList.isSelected()){
            btnServerList.setSelected(true);
            return;
        }
        this.getParentController().loadServerTreeView();
    }
    
    @FXML
    public void gotoUrlList(){
        if (!btnUrlList.isSelected()){
            btnUrlList.setSelected(true);
            return;
        }
        this.getParentController().loadURLTreeView();
    }
    
}
