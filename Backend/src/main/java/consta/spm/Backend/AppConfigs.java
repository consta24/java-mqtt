package consta.spm.Backend;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AppConfigs {

    public static final String MQTT_SERVER_URI = "tcp://localhost:1883";
    public static final String MONGODB_SERVER_URI = "mongodb://localhost:27017";
    public static final String MONGODB_DATA_DATABASE = "MiniProject";
    public static final String MONGODB_DATA_COLLECTION = "Messages";
    public static final String MONGODB_DATA_LOGS = "logs";
    public static final String INSIDE_TEMPERATURE_SENSOR = "INSIDE_TEMPERATURE_SENSOR";
    public static final String OUTSIDE_TEMPERATURE_SENSOR = "OUTSIDE_TEMPERATURE_SENSOR";
    public static final String ENGINE_TEMPERATURE_SENSOR = "ENGINE_TEMPERATURE_SENSOR";
    public static final String ENGINE_STATUS_SENSOR = "ENGINE_STATUS_SENSOR";

    public static final String ENGINE_STATUS_ON = "1";
    public static final String ENGINE_STATUS_OFF = "0";
    public static final int ENGINE_TEMP_OVERHEAT = 110;
    public static final int ENGINE_TEMP_HEAT = 40;

    public static final String ROUTE_PLANNING = "ROUTE_PLANNING";
    public static final String ROUTE_PLANNING_AUTH = "api_key";
    public static final String ROUTE_PLANNING_API_KEY = "303f2429-20b5-443f-9b95-42e518ec57db";
    public static final String ROUTE_PLANNING_LANGUAGE = "en";
    public static final String ROUTE_PLANNING_MODE = "fastest";
    public static final String ROUTE_PLANNING_ALGORITHM = "alternative_route";
    public static final int ROUTE_PLANNING_MAX_PATHS = 10;

    public static final int MILISECONDS_TO_HOURS = 3600000;
    public static final int METERS_TO_KILOMETERS = 1000;


    public static final String START_COORDINATES = "{-10000, -10000}";
    public static final String END_COORDINATES = "{-10001, -10001}";
    public static final String SOS_COORDINATES = "{-11111, -11111}";

    public static final String CAR_GPS_SENSOR = "CAR_GPS_SENSOR";

    public static final List<String> SUBSCRIBED_TOPICS =
            Collections.unmodifiableList(Arrays.asList(
                    INSIDE_TEMPERATURE_SENSOR,
                    OUTSIDE_TEMPERATURE_SENSOR,
                    ENGINE_TEMPERATURE_SENSOR,
                    ENGINE_STATUS_SENSOR,
                    CAR_GPS_SENSOR,
                    ROUTE_PLANNING
            ));
}
