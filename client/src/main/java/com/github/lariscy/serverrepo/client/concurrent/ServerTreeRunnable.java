package com.github.lariscy.serverrepo.client.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steven Lariscy
 */
public class ServerTreeRunnable implements Runnable {
    
    private static final Logger LOG = LoggerFactory.getLogger(ServerTreeRunnable.class);
    
    private ServerTreeManager serverTreeManager;

    public ServerTreeRunnable(ServerTreeManager serverTreeManager) {
        this.serverTreeManager = serverTreeManager;
    }

    @Override
    public void run() {
        LOG.debug("ServerTreeRunnable.run()");
        
        serverTreeManager.generateTestTreeData();
        
        try { Thread.sleep(5000); } catch (InterruptedException ex) {  }
        
        serverTreeManager.addApplication();
        
        try { Thread.sleep(5000); } catch (InterruptedException ex) {  }
        
        serverTreeManager.addServersToNewApplication();
    }
    
}
