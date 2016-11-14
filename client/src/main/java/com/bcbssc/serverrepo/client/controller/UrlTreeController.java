package com.bcbssc.serverrepo.client.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
public class UrlTreeController extends ChildController implements Initializable {
    
    private static final Logger LOG = LoggerFactory.getLogger(UrlTreeController.class);

    @FXML
    private TreeView treeView;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOG.debug("initializing");
        this.generateTestData();
    }

    private void generateTestData() {
        LOG.debug("TEST: generating test data for TreeView");
        TreeItem<String> root = new TreeItem<>("Root");
        root.setExpanded(true);
        
        for (int i=1; i<11; i++){
            TreeItem<String> parentItem = new TreeItem<>("Application "+i);
            for (int j=1; j<6; j++){
                parentItem.getChildren().add(new TreeItem<>("Url "+j));
            }
            root.getChildren().add(parentItem);
        }
        
        treeView.setShowRoot(false);
        treeView.setRoot(root);
    }
    
}
