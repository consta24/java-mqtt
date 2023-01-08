package consta.spm.MqttBroker;

import consta.spm.MqttBroker.datahandler.DataHandler;
import consta.spm.MqttBroker.models.PubSubTopicModel;
import consta.spm.MqttBroker.threads.TopicThreadFactory;
import consta.spm.MqttBroker.threads.TopicThreadPublisher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.ArrayList;
import java.util.List;

import static consta.spm.MqttBroker.configuration.AppConfig.TOPIC_DATA_PATH;

public class MqttBrokerMain {

    private static final Logger LOGGER = LogManager.getLogger(MqttBrokerMain.class);

    public static void main(String[] args) {

        DataHandler dataHandler = new DataHandler();
        List<PubSubTopicModel> pubSubTopicModels = dataHandler.parseData(dataHandler.extractFiles(TOPIC_DATA_PATH));

        TopicThreadFactory topicThreadFactory = new TopicThreadFactory();
        List<Thread> topicThreads = new ArrayList<>();

        try {
            for (PubSubTopicModel pubSubTopicModel : pubSubTopicModels) {
                TopicThreadPublisher topicPublisherThread = new TopicThreadPublisher(pubSubTopicModel);
                topicThreads.add(topicThreadFactory.newThread(topicPublisherThread));
            }
            topicThreads.forEach(Thread::start);
        } catch (MqttException e) {
            LOGGER.error(e);
        }
    }
}
