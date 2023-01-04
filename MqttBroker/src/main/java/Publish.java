import app.AppConfig;
import app.datahandler.DataHandler;
import app.models.Topic;
import app.threads.TopicThreadFactory;
import app.threads.TopicThreadPublisher;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.ArrayList;
import java.util.List;

import static app.AppConfig.MONGODB_DATA_DATABASE;
import static app.AppConfig.TOPIC_DATA_PATH;
import handlers.DatabaseHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Publish {

    private static final Logger LOGGER = LogManager.getLogger(Publish.class);

    public static void main(String[] args) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.connect(MONGODB_DATA_DATABASE);

        DataHandler dataHandler = new DataHandler();
        List<Topic> topics = dataHandler.parseData(dataHandler.extractFiles(TOPIC_DATA_PATH));

        TopicThreadFactory topicThreadFactory = new TopicThreadFactory();
        List<Thread> topicThreads = new ArrayList<>();

        try {
            for (Topic topic : topics) {
                TopicThreadPublisher topicPublisherThread = new TopicThreadPublisher(topic);
                topicThreads.add(topicThreadFactory.newThread(topicPublisherThread));
            }

            topicThreads.forEach(Thread::start);
        } catch (MqttException e){
            LOGGER.error(e);
        } finally {
            databaseHandler.close();
        }
    }
}
