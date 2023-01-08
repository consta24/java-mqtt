package consta.spm.MqttBroker;

import consta.spm.MqttBroker.configuration.AppConfig;
import consta.spm.MqttBroker.models.PubSubTopicModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.UUID;

public class MqttPublisher {

    private static final Logger LOGGER = LogManager.getLogger(MqttPublisher.class);
    private final MqttAsyncClient mqttClient;
    private final MqttConnectOptions connectOptions;

    public MqttPublisher() throws MqttException {
        connectOptions = new MqttConnectOptions();
        connectOptions.setCleanSession(true);
        connectOptions.setAutomaticReconnect(true);
        connectOptions.setConnectionTimeout(10);

        mqttClient = new MqttAsyncClient(AppConfig.MQTT_SERVER_URI, UUID.randomUUID().toString());
    }

    public void connect() throws MqttException {
        IMqttToken token = mqttClient.connect(connectOptions);
        token.waitForCompletion();
    }

    public void publish(PubSubTopicModel pubSubTopicModel) {
        for (String value : pubSubTopicModel.getValues()) {
            try {
                mqttClient.publish(pubSubTopicModel.getName(), value.getBytes(), 0, false);
                LOGGER.info("Published {} with value: {}", pubSubTopicModel.getName(), value);
                Thread.sleep(AppConfig.TopicSleepTimer.getSleepTimer(pubSubTopicModel.getName()));
            } catch (Exception e) {
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

    public void close() throws MqttException {
        mqttClient.disconnect();
        mqttClient.close();
    }

}
