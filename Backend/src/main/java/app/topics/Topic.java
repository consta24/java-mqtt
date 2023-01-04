package app.topics;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;

public interface Topic {
    IMqttMessageListener getListener();
}
