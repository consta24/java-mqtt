package consta.spm.Backend.handlers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Date;

import static consta.spm.Backend.configuration.AppConfig.MONGODB_SERVER_URI;

public class DatabaseHandler {

    private static final Logger LOGGER = LogManager.getLogger(DatabaseHandler.class);
    private final MongoClient mongoClient;
    private MongoDatabase database;
    private static MongoCollection<Document> collection;

    public DatabaseHandler() {
        this.mongoClient = MongoClients.create(MONGODB_SERVER_URI);
    }

    public synchronized static void insertSignal(String topic, MqttMessage message) {
        collection.insertOne(
                new Document()
                        .append("Topic", topic)
                        .append("Signal", new String(message.getPayload()))
                        .append("Timestamp", new Date())
        );
    }

    public void connect(String databaseName) {
        database = mongoClient.getDatabase(databaseName);
    }

    public void close() {
        mongoClient.close();
    }

    public void getCollection(String collectionName) {
        if (database != null) {
            collection = database.getCollection(collectionName);
        } else {
            LOGGER.error("Database is not set");
        }
    }

    public void getCleanCollection(String collectionName) {
        getCollection(collectionName);
        collection.drop();
    }
}
