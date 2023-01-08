import consta.spm.Backend.handlers.DatabaseHandler;
import consta.spm.Backend.handlers.MqttClientHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;

import static consta.spm.Backend.configuration.AppConfig.MONGODB_DATA_COLLECTION;
import static consta.spm.Backend.configuration.AppConfig.MONGODB_DATA_DATABASE;
import static consta.spm.Backend.configuration.AppConfig.MQTT_SERVER_URI;

public class BackendMain {

    private static final Logger LOGGER = LogManager.getLogger(BackendMain.class);

    public static void main(String[] args) {

        DatabaseHandler databaseHandler;
        MqttClientHandler mqttClient;

        try {
            databaseHandler = new DatabaseHandler();
            databaseHandler.connect(MONGODB_DATA_DATABASE);
            databaseHandler.getCleanCollection(MONGODB_DATA_COLLECTION);

            mqttClient = new MqttClientHandler(new MqttClient(MQTT_SERVER_URI, UUID.randomUUID().toString(), new MemoryPersistence()));
            mqttClient.connect(mqttClient.getDefaultConnectionOptions());

            mqttClient.subscribeToSelectedTopics();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }


}

