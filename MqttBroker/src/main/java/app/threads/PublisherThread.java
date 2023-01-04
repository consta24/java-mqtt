package app.threads;

import app.MqttPublisher;
import app.models.Topic;
import org.eclipse.paho.client.mqttv3.MqttException;

public class PublisherThread implements Runnable{

    private final MqttPublisher mqttPublisher = new MqttPublisher();
    private final Topic topic;

    public PublisherThread (Topic topic) throws MqttException {
        mqttPublisher.connect();
        this.topic = topic;
    }
    @Override
    public void run() {

        mqttPublisher.publishDataModel(topic);

    }
}
