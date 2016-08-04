package com.webratio.units.custom.modifiedgooglemaps;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

/**
 * The bean holding information of a Google map location marker.
 */
public final class Marker {

    private String title;
    private String info;
    private String coordinates;
    private boolean infoWindowVisible;

    /**
     * Gets the marker title.
     * 
     * @return the marker title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the marker title.
     * 
     * @param title
     *            the marker title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the marker information.
     * 
     * @return the marker information.
     */
    public String getInfo() {
        return info;
    }

    /**
     * Sets the marker information.
     * 
     * @param info
     *            the marker information.
     */
    public void setInfo(String info) {
        info = StringUtils.replace(info, "\t", "");
        info = StringUtils.replace(info, "\n", "");
        info = StringUtils.replace(info, "\r", "");
        this.info = StringEscapeUtils.escapeJavaScript(info);
    }

    /**
     * Gets the marker coordinates.
     * 
     * @return the marker coordinates.
     */
    public String getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the marker coordinates.
     * 
     * @param coordinates
     *            the marker coordinates.
     */
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Gets <code>true</code> if the info window must be shown on map loading.
     * 
     * @return <code>true</code> if the info window must be shown on map loading.
     */
    public boolean isInfoWindowVisible() {
        return infoWindowVisible;
    }

    /**
     * Set whether the info window must opened on map loading.
     * 
     * @param infoWindowVisible
     *            <code>true</code> to open the info window on map loading.
     */
    public void setInfoWindowVisible(boolean infoWindowVisible) {
        this.infoWindowVisible = infoWindowVisible;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return title + "[" + coordinates + "]";
    }

}
