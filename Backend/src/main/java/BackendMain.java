import consta.spm.Backend.handlers.DatabaseHandler;
import consta.spm.Backend.handlers.MqttClientHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static consta.spm.Backend.configuration.AppConfig.MONGODB_DATA_COLLECTION;
import static consta.spm.Backend.configuration.AppConfig.MONGODB_DATA_DATABASE;
import static consta.spm.Backend.configuration.AppConfig.MQTT_SERVER_URI;

public class BackendMain {

    private static final Logger LOGGER = LogManager.getLogger(BackendMain.class);

    public static void main(String[] args) {

        DatabaseHandler databaseHandler;
        MqttClientHandler mqttClient;

        databaseHandler = new DatabaseHandler();
        databaseHandler.connect(MONGODB_DATA_DATABASE);
        databaseHandler.getCleanCollection(MONGODB_DATA_COLLECTION);

        mqttClient = new MqttClientHandler(MQTT_SERVER_URI);
        mqttClient.connect(mqttClient.getDefaultConnectionOptions());

        mqttClient.subscribeToSelectedTopics();
    }
}

