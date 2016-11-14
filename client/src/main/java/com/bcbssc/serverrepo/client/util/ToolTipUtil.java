package com.bcbssc.serverrepo.client.util;

import java.lang.reflect.Field;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jw38
 * 
 * Code borrowed from:
 * http://stackoverflow.com/questions/26854301/control-javafx-tooltip-delay
 */
public class ToolTipUtil {
    
    private static final Logger LOG = LoggerFactory.getLogger(ToolTipUtil.class);
    
    private static final int TOOLTIP_HOVER_ACTIVATE_DELAY = 50;

    public static void hackTooltipStartTiming(Tooltip tooltip) {
        try {
            Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(true);
            Object objBehavior = fieldBehavior.get(tooltip);

            Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
            fieldTimer.setAccessible(true);
            Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(TOOLTIP_HOVER_ACTIVATE_DELAY)));
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException ex) {
            LOG.error("error changing tooltip hover activate delay", ex);
        }
    }

}
