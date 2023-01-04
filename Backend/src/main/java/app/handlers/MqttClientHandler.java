package app.handlers;

import app.MqttOnMessageCallback;
import app.topics.TopicListenerFactory;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import static app.AppConfigs.SUBSCRIBED_TOPICS;

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

    public void disconnect() throws MqttException {
        mqttClient.disconnect();
    }

    public void subscribeToSelectedTopics() throws MqttException {
        for(String topic : SUBSCRIBED_TOPICS) {
            mqttClient.subscribe(topic, TopicListenerFactory.getListener(topic));
        }
    }

    public void close() throws MqttException {
        mqttClient.close();
    }

    public void connect(MqttConnectOptions connectOptions) throws MqttException {
        mqttClient.connect(connectOptions);
    }
}
