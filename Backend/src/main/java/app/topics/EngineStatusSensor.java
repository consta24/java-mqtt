package app.topics;

import app.handlers.DatabaseHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;

import static app.AppConfigs.*;

public class EngineStatusSensor implements Topic{
    private static final Logger LOGGER = LogManager.getLogger(EngineTemperatureSensor.class);
    private static boolean engineRunning = false;

    @Override
    public IMqttMessageListener getListener() {
        return (topic,msg) -> {
            DatabaseHandler.insertSignal(topic, msg);
            switch (msg.toString()) {
                case ENGINE_STATUS_ON:
                    if(EngineTemperatureSensor.getEngineOverHeat()){
                        LOGGER.info("Engine ON failed. Wait for the temperature to cool down, then try again.");
                    } else {
                        LOGGER.info("Engine ON");
                        engineRunning = true;
                    }
                    break;
                case ENGINE_STATUS_OFF:
                    LOGGER.info("Engine OFF");
                    engineRunning = false;
                    break;
            }
        };
    }

    public synchronized static boolean getEngineRunning(){
        return engineRunning;
    }

    public synchronized static void setEngineRunning(boolean engineRunning){
        EngineStatusSensor.engineRunning = engineRunning;
    }
}
