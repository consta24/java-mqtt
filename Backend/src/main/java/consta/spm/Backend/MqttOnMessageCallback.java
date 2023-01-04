package consta.spm.Backend;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttOnMessageCallback implements MqttCallbackExtended {

    private static final Logger LOGGER = LogManager.getLogger(MqttOnMessageCallback.class);

    @Override
    public void connectComplete(boolean b, String s) {
        LOGGER.info("Connection status: {}, address: {}", b, s);
    }

    @Override
    public void connectionLost(Throwable cause) {
        LOGGER.info("Cause: {}, Message: {}, Trace: {}", cause.getCause(), cause.getMessage(), cause.getStackTrace());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
    }
}