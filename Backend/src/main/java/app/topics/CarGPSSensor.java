package app.topics;

import app.handlers.GPSSensorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;

public class CarGPSSensor implements Topic{

    private static final Logger LOGGER = LogManager.getLogger(CarGPSSensor.class);

    @Override
    public IMqttMessageListener getListener() {

        return ((topic, message) -> {
            LOGGER.info("GPS COORDINATES: {}", message.toString());
            GPSSensorHandler gpsSensor = GPSSensorHandler.getInstance();
            gpsSensor.handleGPSSensorData(message.toString());
        });
    }
}
