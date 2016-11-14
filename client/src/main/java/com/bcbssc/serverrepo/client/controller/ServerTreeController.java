package com.bcbssc.serverrepo.client.controller;

import com.bcbssc.serverrepo.client.concurrent.ServerTreeManager;
import com.bcbssc.serverrepo.client.concurrent.ServerTreeRunnable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
public class ServerTreeController extends ChildController implements Initializable {
    
    private static final Logger LOG = LoggerFactory.getLogger(ServerTreeController.class);

    @FXML
    private TreeView treeView;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOG.debug("initializing");
        
        ServerTreeRunnable serverTreeRunnable = new ServerTreeRunnable(new ServerTreeManager(treeView));
        Thread serverTreeThread = new Thread(serverTreeRunnable, "Server Tree Thread");
        serverTreeThread.setDaemon(true);
        serverTreeThread.start();
        
//        TreeItem<String> root = new TreeItem<>("Root");
//        root.setExpanded(true);
//        
//        this.generateTestTreeData(root);
//        
//        treeView.setShowRoot(false);
//        treeView.setRoot(root);
    }
    
}
