package com.github.lariscy.serverrepo.client.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven Lariscy
 */
public class ServerTreeController extends ChildController implements Initializable {
    
    private static final Logger LOG = LoggerFactory.getLogger(ServerTreeController.class);

    @FXML
    private TreeView<String> treeView;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOG.debug("initializing");
        
//        ServerTreeRunnable serverTreeRunnable = new ServerTreeRunnable();
//        Thread serverTreeThread = new Thread(serverTreeRunnable, "ServerTree Thread");
//        serverTreeThread.setDaemon(true);
//        serverTreeThread.start();
    }
    
}
