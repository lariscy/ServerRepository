package com.github.lariscy.serverrepo.client;

/**
 * @author jw38
 */
public class ClientLocation {
    
    private Double x;
    private Double y;
    private Double width;
    private Double height;
    private boolean maximized;
    
    public ClientLocation(){
        
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public boolean isMaximized() {
        return maximized;
    }

    public void setMaximized(boolean maximized) {
        this.maximized = maximized;
    }
    
    public boolean isPopulated(){
        return (x!=null && y!=null && width!=null && height!=null);
    }

    @Override
    public String toString() {
        return "ClientLocation{" + "x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", maximized=" + maximized + '}';
    }
    
}
