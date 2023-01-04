package app.topics;

import app.handlers.DatabaseHandler;
import app.models.RouteDetails;
import com.graphhopper.directions.api.client.ApiClient;
import com.graphhopper.directions.api.client.ApiException;
import com.graphhopper.directions.api.client.Configuration;
import com.graphhopper.directions.api.client.api.RoutingApi;
import com.graphhopper.directions.api.client.auth.ApiKeyAuth;
import com.graphhopper.directions.api.client.model.RouteResponse;
import com.graphhopper.directions.api.client.model.RouteResponsePath;
import com.graphhopper.directions.api.client.model.RouteResponsePathInstructions;
import com.graphhopper.directions.api.client.model.VehicleProfileId;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static app.AppConfigs.*;

public class RoutePlanning implements Topic {

    private static final Logger LOGGER = LogManager.getLogger(RoutePlanning.class);
    private static RoutingApi routingApi;
    private static String startCoord = null;
    private static String endCoord = null;

    public RoutePlanning() {
        ApiClient apiClient = Configuration.getDefaultApiClient();
        ApiKeyAuth apiKeyAuth = (ApiKeyAuth) apiClient.getAuthentication(ROUTE_PLANNING_AUTH);
        apiKeyAuth.setApiKey(ROUTE_PLANNING_API_KEY);

        routingApi = new RoutingApi();
        routingApi.setApiClient(apiClient);
    }

    private RouteResponse getRoute(String startCoord, String endCoord) {
        try {
            return routingApi.getRoute(Arrays.asList(startCoord, endCoord), Collections.emptyList(), Collections.emptyList(), VehicleProfileId.CAR, ROUTE_PLANNING_LANGUAGE, false, Collections.emptyList(), false, true, true, true, false, null, false, ROUTE_PLANNING_MODE, Collections.emptyList(), null, null, null, null, ROUTE_PLANNING_ALGORITHM, null, null, ROUTE_PLANNING_MAX_PATHS, null, null);
        } catch (ApiException apiException) {
            LOGGER.error(apiException);
            throw new RuntimeException(apiException);
        }
    }

    private List<String> getValidCoordinates(RouteResponsePath path) {
        String gpsCoordinates = path.getPoints().toString();
        return Arrays.asList(gpsCoordinates.substring(32, gpsCoordinates.length() - 3).split("\\], \\["));
    }

    private List<RouteDetails> getRoutesDetails(RouteResponse routeResponse) {

        List<RouteDetails> routesDetails = new ArrayList<>();

        for (RouteResponsePath path : routeResponse.getPaths()) {

            float routeTime = 0;
            float routeDistance = 0;
            List<String> intersectionsCoord = new ArrayList<>();

            for (RouteResponsePathInstructions instructions : path.getInstructions()) {
                routeTime += instructions.getTime();
                routeDistance += instructions.getDistance();
                intersectionsCoord.add(getValidCoordinates(path).get(instructions.getInterval().get(1)));
            }
            routesDetails.add(new RouteDetails(intersectionsCoord, routeTime, routeDistance));
        }
        return routesDetails;
    }

    private void showRoutes(List<RouteDetails> routesDetails) {
        for (RouteDetails routeDetails : routesDetails) {
            routeDetails.logData();
        }
    }

    private void createRoutePlanning(MqttMessage msg) {
        String validCoords = String.valueOf(msg).substring(1, String.valueOf(msg).length() - 1);

        if (startCoord == null) {
            startCoord = validCoords;
            LOGGER.info("startCoord value = [{}] received.", startCoord);
            LOGGER.info("Waiting for endCoord");
        } else {
            endCoord = validCoords;
            LOGGER.info("endCoord value = [{}] received.", endCoord);
            LOGGER.info("Route Planning started");
            showRoutes(getRoutesDetails(getRoute(startCoord, endCoord)));
        }
    }

    @Override
    public IMqttMessageListener getListener() {
        return (topic, msg) -> {
            DatabaseHandler.insertSignal(topic, msg);
            createRoutePlanning(msg);
        };
    }
}
