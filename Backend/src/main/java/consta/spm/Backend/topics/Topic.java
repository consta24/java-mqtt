package consta.spm.Backend.topics;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;

public interface Topic {
    IMqttMessageListener getListener();
}
