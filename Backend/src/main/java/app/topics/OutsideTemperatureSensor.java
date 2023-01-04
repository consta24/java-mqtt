package app.topics;

import app.handlers.DatabaseHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;

public class OutsideTemperatureSensor implements Topic {

    private static final Logger LOGGER = LogManager.getLogger(OutsideTemperatureSensor.class);

    @Override
    public IMqttMessageListener getListener() {
        return (topic, msg) -> {
            DatabaseHandler.insertSignal(topic, msg);
            LOGGER.info("Topic {} and message {} has been saved into the database", topic, msg);
        };
    }
}
