package com.webratio.units.custom.modifiedgooglemaps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

import com.webratio.rtx.RTXCachedContentUnitService;
import com.webratio.rtx.RTXException;
import com.webratio.rtx.RTXManager;
import com.webratio.rtx.beans.ExtendedContentUnitBean;
import com.webratio.rtx.core.BeanHelper;
import com.webratio.rtx.core.DescriptorHelper;
import com.webratio.rtx.db.AbstractDBService;

/**
 * Runtime service for Google map units based on a persistent entity.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ModifiedGoogleMapsUnitService extends AbstractDBService implements RTXCachedContentUnitService {

    private String mapType;
    private String mapZoomLevel;
    private String mapCoordinates;
    private String mapWidth;
    private String mapHeight;
    private boolean useSensor;
    private boolean isAdjustable;

    /** The list of the static markers */
    private List defaultMarkers;

    /**
     * Constructs a new service.
     * 
     * @param id
     *            the unique identifier of the service.
     * @param mgr
     *            the shared runtime manager.
     * @param descr
     *            the XML descriptor.
     * @throws RTXException
     *             if an error occurs building the service.
     */
    public ModifiedGoogleMapsUnitService(String id, RTXManager mgr, Element descr) throws RTXException {
        super(id, mgr, descr);
        mapType = DescriptorHelper.getChildValue(descr, "MapType", true, this);
        mapZoomLevel = DescriptorHelper.getChildValue(descr, "MapZoomLevel", true, this);
        mapCoordinates = DescriptorHelper.getChildValue(descr, "MapCoordinates", true, this);
        mapWidth = DescriptorHelper.getChildValue(descr, "MapWidth", true, this);
        mapHeight = DescriptorHelper.getChildValue(descr, "MapHeight", true, this);
        useSensor = "true".equals(DescriptorHelper.getChildValue(descr, "UseSensor", true, this));
        isAdjustable = "true".equals(DescriptorHelper.getChildValue(descr, "IsAdjustable", false, this));

        /* create the default markers */
        defaultMarkers = new ArrayList();
        List markerElements = descr.selectNodes("Markers/Marker");
        for (Iterator iterator = markerElements.iterator(); iterator.hasNext();) {
            Element markerElement = (Element) iterator.next();
            Marker marker = new Marker();
            marker.setTitle(markerElement.attributeValue("title"));
            marker.setInfo(markerElement.getText());
            marker.setCoordinates(markerElement.attributeValue("coordinates"));
            marker.setInfoWindowVisible("true".equals(markerElement.attributeValue("infoWindowVisible")));
            defaultMarkers.add(marker);
        }

        /* sets the map coordinates if blank */
        if (StringUtils.isBlank(mapCoordinates) && !defaultMarkers.isEmpty()) {
            mapCoordinates = ((Marker) defaultMarkers.get(0)).getCoordinates();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.webratio.rtx.RTXContentUnitService#computeParameterValue(java.lang.String, java.util.Map, java.util.Map)
     */
    public Object computeParameterValue(String outputParamName, Map pageContext, Map sessionContext) throws RTXException {
        Object unitBean = getUnitBean(pageContext, sessionContext);
        if (unitBean == null) {
            return null;
        }
        if (outputParamName.equalsIgnoreCase("mapCoordinates")) {
            return BeanHelper.getBeanProperty(unitBean, "mapCoordinates", this);
        } else {
            return BeanHelper.getBeanProperty(unitBean, outputParamName, this);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.webratio.rtx.RTXContentUnitService#execute(java.util.Map, java.util.Map)
     */
    public Object execute(Map pageContext, Map sessionContext) throws RTXException {
        return getUnitBean(pageContext, sessionContext);
    }

    /**
     * Gets (or recomputes) the unit bean.
     * 
     * @param pageContext
     *            a set of name-to-object bindings, preloaded with values of parameters (having scope = page).
     * @param sessionContext
     *            a set of name-to-object bindings, preloaded with values of parameters (having scope = session).
     * @return the unit bean.
     * @throws RTXException
     *             if an error occurred computing the bean.
     */
    protected Object getUnitBean(Map pageContext, Map sessionContext) throws RTXException {
        Object unitBean = pageContext.get('_' + getId());
        if (unitBean == null) {
            unitBean = createUnitBean(pageContext, sessionContext);
        }
        pageContext.put('_' + getId(), unitBean);
        return unitBean;
    }

    /**
     * Creates the unit bean.
     * 
     * @param pageContext
     *            a set of name-to-object bindings, preloaded with values of parameters (having scope = page).
     * @param sessionContext
     *            a set of name-to-object bindings, preloaded with values of parameters (having scope = session).
     * @return the unit bean.
     * @throws RTXException
     *             if an error occurred computing the bean.
     */
    public Object createUnitBean(Map pageContext, Map sessionContext) throws RTXException {
        ExtendedContentUnitBean bean = new ExtendedContentUnitBean();
        bean.put("mapType", StringUtils.defaultIfEmpty(BeanHelper.asString(pageContext.get(getId() + ".type")), mapType));
        bean.put("mapZoomLevel", StringUtils.defaultIfEmpty(BeanHelper.asString(pageContext.get(getId() + ".zoom")), mapZoomLevel));
        bean.put("mapWidth", StringUtils.defaultIfEmpty(BeanHelper.asString(pageContext.get(getId() + ".width")), mapWidth));
        bean.put("mapHeight", StringUtils.defaultIfEmpty(BeanHelper.asString(pageContext.get(getId() + ".height")), mapHeight));
        bean.put("useSensor", Boolean.valueOf(useSensor));
        bean.put("isAdjustable", Boolean.valueOf(isAdjustable));

        /* computes the map coordinates */
        bean.put("mapCoordinates",
                StringUtils.defaultIfEmpty(BeanHelper.asString(pageContext.get(getId() + ".coords")), mapCoordinates));

        /* compute the map markers */
        String[] markerCoords = BeanHelper.asStringArray(pageContext.get(getId() + ".markerCoords"));
        Set markers = new LinkedHashSet();
        markers.addAll(defaultMarkers);
        if (!ArrayUtils.isEmpty(markerCoords)) {
            String[] titles = BeanHelper.asStringArray(pageContext.get(getId() + ".markerTitle"));
            String[] infos = BeanHelper.asStringArray(pageContext.get(getId() + ".markerInfo"));
            int size = Math.min(titles.length, infos.length);
            for (int i = 0; i < size; i++) {
                Marker marker = new Marker();
                marker.setTitle(titles[i]);
                marker.setInfo(infos[i]);
                marker.setCoordinates(markerCoords[i]);
                markers.add(marker);
            }
        }
        bean.put("markers", markers.toArray());
        return bean;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.webratio.rtx.RTXService#dispose()
     */
    public void dispose() {
    }

}
