package app;

public class AppConfig {
    public static final String MQTT_SERVER_URI = "tcp://localhost:1883";
    public static final String MONGODB_SERVER_URI = "mongodb://localhost:27017";
    public static final String MONGODB_DATA_DATABASE = "MqttBroker";
    public static final String TOPIC_DATA_PATH = "data";
    public static final String EXTENSION_XML = ".xml";
    public static final String EXTENSION_CSV = ".csv";

    public enum TopicSleepTimer {
        INSIDE_TEMPERATURE_SENSOR(15000),
        OUTSIDE_TEMPERATURE_SENSOR(15000),
        ENGINE_TEMPERATURE_SENSOR(2000),
        ENGINE_STATUS_SENSOR(2000),
        ROUTE_PLANNING(0),
        DEFAULT_TOPIC_TIMER(5000);

        private final int timer;

        TopicSleepTimer(int timer) {
            this.timer = timer;
        }
        public int getTimer() {
            return timer;
        }
        public String getName(){
            return name();
        }
        public static int getSleepTimer(String topic) {
            for (TopicSleepTimer timer : TopicSleepTimer.values()) {
                if(topic.equals(timer.getName()))
                    return timer.getTimer();
            }
            return DEFAULT_TOPIC_TIMER.getTimer();
        }
    }
}
