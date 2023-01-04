package consta.spm.MqttBroker.threads;

import consta.spm.MqttBroker.MqttPublisher;
import consta.spm.MqttBroker.models.PubSubTopicModel;
import org.eclipse.paho.client.mqttv3.MqttException;

public class PublisherThread implements Runnable{

    private final MqttPublisher mqttPublisher = new MqttPublisher();
    private final PubSubTopicModel pubSubTopicModel;

    public PublisherThread (PubSubTopicModel pubSubTopicModel) throws MqttException {
        mqttPublisher.connect();
        this.pubSubTopicModel = pubSubTopicModel;
    }
    @Override
    public void run() {

        mqttPublisher.publish(pubSubTopicModel);

    }
}
