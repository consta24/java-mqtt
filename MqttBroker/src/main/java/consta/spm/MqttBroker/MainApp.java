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

import static consta.spm.MqttBroker.AppConfig.MONGODB_DATA_DATABASE;
import static consta.spm.MqttBroker.AppConfig.TOPIC_DATA_PATH;

public class MainApp {

    private static final Logger LOGGER = LogManager.getLogger(MainApp.class);

    public static void main(String[] args) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.connect(MONGODB_DATA_DATABASE);

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
        } finally {
            databaseHandler.close();
        }
    }
}
