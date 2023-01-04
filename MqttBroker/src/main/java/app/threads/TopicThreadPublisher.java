package app.threads;

import app.MqttPublisher;
import app.models.Topic;
import org.eclipse.paho.client.mqttv3.MqttException;


public class TopicThreadPublisher implements Runnable {

    private final MqttPublisher mqttPublisher = new MqttPublisher();
    private final Topic topic;

    public TopicThreadPublisher(Topic topic) throws MqttException {
        mqttPublisher.connect();
        this.topic = topic;
    }

    public void run(){
        mqttPublisher.publish(topic);
    }
}
