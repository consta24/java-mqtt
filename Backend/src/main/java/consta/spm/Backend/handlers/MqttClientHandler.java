package consta.spm.Backend.handlers;

import consta.spm.Backend.MqttOnMessageCallback;
import consta.spm.Backend.topics.TopicListenerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;

import static consta.spm.Backend.configuration.AppConfig.SUBSCRIBED_TOPICS;

public class MqttClientHandler {

    private static final Logger LOGGER = LogManager.getLogger(MqttClientHandler.class);

    private final MqttClient mqttClient;

    public MqttClientHandler(String serverUri) {
        try {
            this.mqttClient = new MqttClient(serverUri, UUID.randomUUID().toString(), new MemoryPersistence());
            this.mqttClient.setCallback(new MqttOnMessageCallback());
        } catch (MqttException e) {
            LOGGER.error(e);
            throw new RuntimeException("MqttException occurred: " + e);
        }
    }

    public MqttConnectOptions getDefaultConnectionOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(10);
        return options;
    }

    public void subscribeToSelectedTopics() {
        for (String topic : SUBSCRIBED_TOPICS) {
            try {
                mqttClient.subscribe(topic, TopicListenerFactory.getListener(topic));
            } catch (MqttException e) {
                LOGGER.error(e);
            }
        }
    }

    public void connect(MqttConnectOptions connectOptions) {
        try {
            mqttClient.connect(connectOptions);
        } catch (MqttException e) {
            LOGGER.error(e);
        }
    }

    public void disconnect() {
        try {
            mqttClient.disconnect();
        } catch (MqttException e) {
            LOGGER.error(e);
        }
    }

    public void close() {
        try {
            mqttClient.close();
        } catch (MqttException e) {
            LOGGER.error(e);
        }
    }
}
