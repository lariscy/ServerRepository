package com.github.lariscy.serverrepo.client.util;

import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven Lariscy
 */
public class AppProps {
    
    private static final Logger LOG = LoggerFactory.getLogger(AppProps.class);

    private final Properties PROPS;
    private static final String NOT_FOUND = "!!PropertyNotFound!!";
    
    public AppProps(String fileName) {
        this.PROPS = new Properties();
        this.loadProps(fileName);
    }
    
    private void loadProps(String fileName){
        LOG.debug("loading properties from [{}]", fileName);
        try {
            PROPS.load(getClass().getClassLoader().getResourceAsStream(fileName));
        } catch (IOException | NullPointerException ex) {
            LOG.error("exception while loading [{}]", fileName, ex);
        }
    }
    
    public String getProp(String key){
        return this.getProp(key, NOT_FOUND);
    }
    
    public String getProp(String key, String defaultVal){
        return this.PROPS.getProperty(key, defaultVal);
    }
    
}
