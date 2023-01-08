package consta.spm.Backend.handlers;

import consta.spm.Backend.MqttOnMessageCallback;
import consta.spm.Backend.topics.TopicListenerFactory;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import static consta.spm.Backend.configuration.AppConfig.SUBSCRIBED_TOPICS;

public class MqttClientHandler {

    private final MqttClient mqttClient;

    public MqttClientHandler(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
        this.mqttClient.setCallback(new MqttOnMessageCallback());
    }

    public MqttConnectOptions getDefaultConnectionOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(10);
        return options;
    }

    public void subscribeToSelectedTopics() throws MqttException {
        for (String topic : SUBSCRIBED_TOPICS) {
            mqttClient.subscribe(topic, TopicListenerFactory.getListener(topic));
        }
    }

    public void connect(MqttConnectOptions connectOptions) throws MqttException {
        mqttClient.connect(connectOptions);
    }

    public void disconnect() throws MqttException {
        mqttClient.disconnect();
    }

    public void close() throws MqttException {
        mqttClient.close();
    }
}
