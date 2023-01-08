package consta.spm.Backend.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static consta.spm.Backend.configuration.AppConfig.METERS_TO_KILOMETERS;
import static consta.spm.Backend.configuration.AppConfig.MILISECONDS_TO_HOURS;

public class RouteDetailsModel {

    private static final Logger LOGGER = LogManager.getLogger(RouteDetailsModel.class);

    private static int count = 0;
    private final int routeNumber;
    private final List<String> intersectionsCoordinates;
    private final float routeTime;
    private final float routeDistance;

    public RouteDetailsModel(List<String> intersectionsCoordinates, float routeTime, float routeDistance) {
        this.routeNumber = count;
        count++;
        this.intersectionsCoordinates = intersectionsCoordinates;
        this.routeTime = routeTime / MILISECONDS_TO_HOURS;
        this.routeDistance = routeDistance / METERS_TO_KILOMETERS;
    }

    public void logData() {
        LOGGER.info("Route [{}] average speed: {}", routeNumber, routeDistance / routeTime);
        LOGGER.info("Route [{}] distance: {} km", routeNumber, routeDistance);
        LOGGER.info("Route [{}] time: {} hours", routeNumber, routeTime);
        LOGGER.info("Route [{}] intersection coordinates: {}", routeNumber, intersectionsCoordinates);
    }
}
