package consta.spm.MqttBroker.threads;

import consta.spm.MqttBroker.MqttPublisher;
import consta.spm.MqttBroker.models.PubSubTopicModel;
import org.eclipse.paho.client.mqttv3.MqttException;


public class TopicThreadPublisher implements Runnable {

    private final MqttPublisher mqttPublisher = new MqttPublisher();
    private final PubSubTopicModel pubSubTopicModel;

    public TopicThreadPublisher(PubSubTopicModel pubSubTopicModel) throws MqttException {
        mqttPublisher.connect();
        this.pubSubTopicModel = pubSubTopicModel;
    }

    public void run(){
        mqttPublisher.publish(pubSubTopicModel);
    }
}
