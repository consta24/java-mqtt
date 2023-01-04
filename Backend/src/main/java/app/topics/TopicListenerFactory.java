package app.topics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static app.AppConfigs.*;

public class TopicListenerFactory {
    private static final Logger LOGGER = LogManager.getLogger(TopicListenerFactory.class);
    private static final Map<String,Topic> factoryMap =
            Collections.unmodifiableMap(new HashMap<String,Topic>() {{
                put(CAR_GPS_SENSOR, new CarGPSSensor());
                put(INSIDE_TEMPERATURE_SENSOR, new InsideTemperatureSensor());
                put(OUTSIDE_TEMPERATURE_SENSOR, new OutsideTemperatureSensor());
                put(ENGINE_TEMPERATURE_SENSOR, new EngineTemperatureSensor());
                put(ENGINE_STATUS_SENSOR, new EngineStatusSensor());
                put(ROUTE_PLANNING, new RoutePlanning());
            }});

    public static IMqttMessageListener getListener(String topicName) {
        Topic topic = factoryMap.get(topicName);
        if (topic != null) {
            return topic.getListener();
        } else {
            LOGGER.error("Cannot get listener, topic not found");
            throw new NullPointerException();
        }
    }
}