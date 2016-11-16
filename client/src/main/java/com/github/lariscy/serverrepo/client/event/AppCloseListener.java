package com.github.lariscy.serverrepo.client.event;

import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven Lariscy
 */
public class AppCloseListener implements ActionListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(AppCloseListener.class);

    private SystemTray tray;
    private TrayIcon trayIcon;
    
    public AppCloseListener(){ }
    
    public AppCloseListener(SystemTray tray, TrayIcon trayIcon){
        this.tray = tray;
        this.trayIcon = trayIcon;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        LOG.debug("close request received");
        //@TODO do stuff here to save application state (window size, location), etc. for next load
        
        Platform.runLater(Platform::exit);
        
        if (tray!=null && trayIcon!=null){
            tray.remove(trayIcon);
        }
    }
    
}
