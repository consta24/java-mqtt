import app.AppConfigs;
import app.handlers.DatabaseHandler;
import app.handlers.MqttClientHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;

import static app.AppConfigs.*;

public class Client {

    private static long id = 1;
    private static final Logger LOGGER = LogManager.getLogger(Client.class);

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

