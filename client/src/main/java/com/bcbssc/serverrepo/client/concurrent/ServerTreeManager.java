package com.bcbssc.serverrepo.client.concurrent;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
public class ServerTreeManager {
    
    private static final Logger LOG = LoggerFactory.getLogger(ServerTreeManager.class);
    
    private TreeView treeView;

    public ServerTreeManager(TreeView treeView) {
        this.treeView = treeView;
    }
    
    public void generateTestTreeData(){
        LOG.debug("TEST: generating test data for TreeView");
        TreeItem<String> root = new TreeItem<>("Root");
        root.setExpanded(true);
        
        for (int i=1; i<11; i++){
            TreeItem<String> parentItem = new TreeItem<>("Application "+i);
            for (int j=1; j<6; j++){
                parentItem.getChildren().add(new TreeItem<>("Server "+j));
            }
            root.getChildren().add(parentItem);
        }
        
        treeView.setShowRoot(false);
        Platform.runLater(() -> {
            treeView.setRoot(root);
        });
    }
    
    public void addApplication(){
        LOG.debug("TEST: adding new application to TreeView");
        Platform.runLater(() -> {
            treeView.getRoot().getChildren().add(new TreeItem<>("Application 11"));
        });
    }

    void addServersToNewApplication() {
        LOG.debug("TEST: adding servers to new application in TreeView");
        ObservableList appList = treeView.getRoot().getChildren();
        Platform.runLater(() -> {
            ((TreeItem) appList.get(appList.size() - 1)).getChildren().add(new TreeItem<>("Server 1"));
        });
    }
    
}
