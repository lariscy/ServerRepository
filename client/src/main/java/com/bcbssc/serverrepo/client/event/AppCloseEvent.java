package com.bcbssc.serverrepo.client.event;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 */
public class AppCloseEvent implements EventHandler {
    
    private static final Logger log = LoggerFactory.getLogger(AppCloseEvent.class);

    @Override
    public void handle(Event event) {
        log.debug("close request received");
        
        event.consume();
        Platform.exit();
    }
    
}
