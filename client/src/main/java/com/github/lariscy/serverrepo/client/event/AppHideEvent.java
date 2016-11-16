package com.github.lariscy.serverrepo.client.event;

import com.github.lariscy.serverrepo.client.MainApp;
import com.github.lariscy.serverrepo.client.controller.ConfirmboxController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven Lariscy
 */
public class AppHideEvent implements EventHandler {
    
    private static final Logger LOG = LoggerFactory.getLogger(AppHideEvent.class);
    
    private static Stage stage;
    private static boolean wasShown = false;

    public AppHideEvent(){
        init();
    }
    
    private void init(){
//        stage = new Stage();
//        stage.initStyle(StageStyle.UNDECORATED);
//        ConfirmboxView confirmboxView = new ConfirmboxView();
//        ConfirmboxController confirmboxController = (ConfirmboxController) confirmboxView.getPresenter();
//        confirmboxController.setTitle(MainApp.getAppProps().getProp("application.name", "default.title"));
//        confirmboxController.setMessage("Application minimized to SystemTray."+
//                "\n\n"+
//                "*This message will not reappear");
//        Scene scene = new Scene(confirmboxView.getView());
//        scene.getStylesheets().add(this.getClass().getResource(MainApp.MAIN_CSS).toExternalForm());
//        stage.setScene(scene);
    }
    
    @Override
    public void handle(Event event) {
        LOG.debug("request to hide stage");
//        
        event.consume();
        MainApp.hideStage();
//        
//        if (!wasShown){
//            stage.show();
//            this.setStagePosition(stage);
//            wasShown = true;
//        }
    }
    
    private void setStagePosition(Stage stage){
        double padding = 5.0;
        // lower right hand corner of screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(screenBounds.getMinX() + screenBounds.getWidth() - stage.getWidth() - padding);
        stage.setY(screenBounds.getMinY() + screenBounds.getHeight() - stage.getHeight() - padding);
        stage.show();
    }
    
}
