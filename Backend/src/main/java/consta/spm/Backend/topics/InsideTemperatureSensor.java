package consta.spm.Backend.topics;

import consta.spm.Backend.handlers.DatabaseHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;

public class InsideTemperatureSensor implements Topic {

    private static final Logger LOGGER = LogManager.getLogger(InsideTemperatureSensor.class);

    @Override
    public IMqttMessageListener getListener() {
        return (topic, msg) -> {
            DatabaseHandler.insertSignal(topic, msg);
            LOGGER.info("Topic {} and message {} has been saved into the database", topic, msg);
        };
    }
}
