package com.github.lariscy.serverrepo.client.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Steven Lariscy
 */
public class ConfirmboxController implements Initializable {

    @FXML
    private Rectangle rectangle;
    @FXML
    private ImageView imageView;
    @FXML
    private Label lblTitle;
    @FXML
    private Line line;
    @FXML
    private Label lblMessage;
    @FXML
    private Label lblClose;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setTitle(String title){
        lblTitle.setText(title);
    }
    
    public void setMessage(String message){
        lblMessage.setText(message);
    }
    
    @FXML
    private void closeIt(){
        ((Stage) lblClose.getScene().getWindow()).hide();
    }
    
}
