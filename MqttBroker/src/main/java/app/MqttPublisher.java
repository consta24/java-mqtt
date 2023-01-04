package app;

import app.models.Topic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;

import java.util.UUID;

import static app.AppConfig.MQTT_SERVER_URI;
import static app.AppConfig.TopicSleepTimer;

public class MqttPublisher {

    private static final Logger LOGGER = LogManager.getLogger(MqttPublisher.class);
    private final MqttAsyncClient mqttClient;
    private final MqttConnectOptions connectOptions;

    public MqttPublisher() throws MqttException {
        connectOptions = new MqttConnectOptions();
        connectOptions.setCleanSession(true);
        connectOptions.setAutomaticReconnect(true);
        connectOptions.setConnectionTimeout(10);

        mqttClient = new MqttAsyncClient(MQTT_SERVER_URI, UUID.randomUUID().toString());
    }

    public void connect() throws MqttException {
        IMqttToken token = mqttClient.connect(connectOptions);
        token.waitForCompletion();
    }

    public void publish(Topic topic) {
        for(String value : topic.getValues()){
            try {
                mqttClient.publish(topic.getName(), value.getBytes(), 0, false);
                LOGGER.info("Published {} with value: {}", topic.getName(), value);
                Thread.sleep(TopicSleepTimer.getSleepTimer(topic.getName()));
            } catch(Exception e) {
                LOGGER.error(e);
            }
        }
    }

    public String getMqttURI() {
        return mqttClient.getServerURI();
    }
    public String getClientId() {
        return mqttClient.getClientId();
    }
    public void close() throws MqttException{
        mqttClient.disconnect();
        mqttClient.close();
    }

}
