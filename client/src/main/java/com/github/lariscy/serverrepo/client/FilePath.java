package com.github.lariscy.serverrepo.client;

/**
 * @author Steven Lariscy
 */
public class FilePath {
    
    private static final String PACKAGE_BASE = "/com/github/lariscy/serverrepo/client/";
    
    /**
     * FXML
     */
    private static final String FXML_BASE = PACKAGE_BASE + "view/";
    //
    public static final String FXML_APPCONTAINER = FXML_BASE + "AppContainer.fxml";
    public static final String FXML_SERVERTREE = FXML_BASE+ "ServerTree.fxml";
    public static final String FXML_URLTREE = FXML_BASE + "UrlTree.fxml";
    
    /**
     * CSS
     */
    private static final String CSS_BASE = PACKAGE_BASE + "css/";
    //
    public static final String CSS_APP = CSS_BASE + "app.css";
    
    /**
     * Images
     */
    private static final String IMG_BASE = PACKAGE_BASE + "img/";
    //
    public static final String IMG_APP_ICON = IMG_BASE + "equipment.png";
    
}
