package com.github.lariscy.serverrepo.client;

import com.github.lariscy.serverrepo.client.event.AppCloseEvent;
import com.github.lariscy.serverrepo.client.event.AppCloseListener;
import com.github.lariscy.serverrepo.client.event.AppHideEvent;
import com.github.lariscy.serverrepo.client.guice.ServerRepoGuiceModule;
import com.github.lariscy.serverrepo.client.net.ServerRepoClient;
import com.github.lariscy.serverrepo.client.util.AppProps;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import net.engio.mbassy.bus.MBassador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven Lariscy
 */
public class MainApp extends Application {
    
    private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);
    
    private static AppProps appProps;
    private static Stage primaryStage;
    private static ClientLocation clientLocation;
    private static Injector injector = Guice.createInjector(new ServerRepoGuiceModule());
    private static ServerRepoClient client = new ServerRepoClient();
    
    @Inject
    private MBassador eventBus;
    
    @Override
    public void start(Stage primaryStage){
        LOG.info("starting application");
        
        MainApp.primaryStage = primaryStage;
        clientLocation = new ClientLocation();
        
        appProps = new AppProps("application.properties");
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.setLocation(getClass().getResource(FilePath.FXML_APPCONTAINER));
            fxmlLoader.setControllerFactory(injector::getInstance);
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            scene.getStylesheets().add(this.getClass().getResource(FilePath.CSS_APP).toExternalForm());
            primaryStage.setScene(scene);
        } catch (IOException ex) {
            LOG.error("error loading FXML [{}], exiting...", FilePath.FXML_APPCONTAINER, ex);
            Platform.exit();
        }
        
        Platform.setImplicitExit(false);
        javax.swing.SwingUtilities.invokeLater(this::addAppToTray);
        
        primaryStage.setTitle(this.getTitle());
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(FilePath.IMG_APP_ICON)));
        primaryStage.setOnCloseRequest(
            (java.awt.SystemTray.isSupported() ? new AppHideEvent() : new AppCloseEvent())
        );
        
        LOG.debug("showing main stage");
        this.showStage();
    }

    private String getTitle(){
        return new StringBuilder()
                .append(MainApp.getAppProps().getProp("application.name", "app.name"))
                .append(" - ")
                .append(MainApp.getAppProps().getProp("application.version", "app.version"))
                .toString();
    }
    
    public static AppProps getAppProps(){
        return appProps;
    }
    
    public static Stage getPrimaryStage(){
        return primaryStage;
    }
    
    public static Injector getInjector(){
        return injector;
    }
    
    public static ServerRepoClient getClient(){
        return client;
    }
    
    private void addAppToTray(){
        try {
            java.awt.Toolkit.getDefaultToolkit();

            if (!java.awt.SystemTray.isSupported()) {
                LOG.error("java.awt.SystemTray is not supported");
                return;
            }

            java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();

            java.awt.Image image = ImageIO.read(this.getClass().getResourceAsStream(FilePath.IMG_APP_ICON));
            java.awt.TrayIcon trayIcon = new java.awt.TrayIcon(image, MainApp.getAppProps().getProp("application.name", "app.name"));

            trayIcon.addActionListener(event -> Platform.runLater(this::showStage));

            java.awt.MenuItem openItem = new java.awt.MenuItem("Open");
            openItem.addActionListener(event -> Platform.runLater(this::showStage));

            java.awt.Font defaultFont = java.awt.Font.decode(null);
            java.awt.Font boldFont = defaultFont.deriveFont(java.awt.Font.BOLD);
            openItem.setFont(boldFont);

            java.awt.MenuItem exitItem = new java.awt.MenuItem("Exit");
            exitItem.addActionListener(new AppCloseListener(tray, trayIcon));

            final java.awt.PopupMenu popup = new java.awt.PopupMenu();
            popup.add(openItem);
            popup.addSeparator();
            popup.add(exitItem);
            trayIcon.setPopupMenu(popup);

            tray.add(trayIcon);
        } catch (java.awt.AWTException | IOException ex) {
            LOG.error("unable to init system tray", ex);
        }
    }
    
    public static void hideStage() {
        if (primaryStage != null) {
            clientLocation.setMaximized(primaryStage.isMaximized());
            
            if (!primaryStage.isMaximized()){
                clientLocation.setX(primaryStage.getX());
                clientLocation.setY(primaryStage.getY());
                clientLocation.setWidth(primaryStage.getWidth());
                clientLocation.setHeight(primaryStage.getHeight());
                clientLocation.setMaximized(primaryStage.isMaximized());
            }
            
            LOG.debug("hideStage - " + clientLocation.toString());
            primaryStage.hide();
        }
    }
    
    public void showStage() {
        if (primaryStage != null) {
            if (clientLocation.isPopulated()){
                primaryStage.setMaximized(clientLocation.isMaximized());
                primaryStage.setX(clientLocation.getX());
                primaryStage.setY(clientLocation.getY());
                primaryStage.setWidth(clientLocation.getWidth());
                primaryStage.setHeight(clientLocation.getHeight());
            }
            
            LOG.debug("showStage - " + clientLocation.toString());
            primaryStage.show();
            primaryStage.toFront();
        }
    }
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
