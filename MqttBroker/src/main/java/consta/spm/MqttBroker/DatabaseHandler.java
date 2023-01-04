package consta.spm.MqttBroker;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static consta.spm.MqttBroker.AppConfig.MONGODB_SERVER_URI;


public class DatabaseHandler {

    private final MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public DatabaseHandler() {
        this.mongoClient = MongoClients.create(MONGODB_SERVER_URI);
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
            System.out.println("Database is not set");
        }
    }

    public void getCleanCollection(String collectionName) {
        getCollection(collectionName);
        collection.drop();
    }
}
