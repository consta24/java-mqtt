package consta.spm.Backend.topics;

import consta.spm.Backend.configuration.AppConfig;
import consta.spm.Backend.handlers.DatabaseHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;

public class EngineTemperatureSensor implements Topic {
    private static final Logger LOGGER = LogManager.getLogger(EngineTemperatureSensor.class);

    private static int engineTemperature = 0;
    private static int engineOverHeatCount = 0;

    private volatile static boolean engineHeat = false;
    private volatile static boolean engineOverHeat = false;

    @Override
    public IMqttMessageListener getListener() {

        return (topic, msg) -> {
            DatabaseHandler.insertSignal(topic, msg);
            engineTemperature = Integer.parseInt(msg.toString());

            LOGGER.info("ENGINE TEMPERATURE: {}", engineTemperature);

            if (engineTemperature >= AppConfig.ENGINE_TEMP_HEAT && !engineHeat) {
                LOGGER.info("Engine temperature has reached over {} degrees, AC system can function", AppConfig.ENGINE_TEMP_HEAT);
                engineHeat = true;
            }
            if (engineTemperature >= AppConfig.ENGINE_TEMP_OVERHEAT && !engineOverHeat && EngineStatusSensor.getEngineRunning()) {
                LOGGER.info("Engine temperature has reached over {} degrees, engine will stop in {} seconds", AppConfig.ENGINE_TEMP_OVERHEAT, 6 - engineOverHeatCount * 2);
                engineOverHeatCount++;
            }
            if (engineOverHeatCount == 4 && !engineOverHeat) {
                LOGGER.info("Engine force stopped because of overheating");
                EngineStatusSensor.setEngineRunning(false);
                engineOverHeat = true;
            }
            if (engineTemperature < AppConfig.ENGINE_TEMP_OVERHEAT) {
                engineOverHeatCount = 0;
                if (engineOverHeat) {
                    LOGGER.info("Temperature back to normal, you can now start the engine");
                    EngineStatusSensor.setEngineRunning(true);
                    engineOverHeat = false;
                }
            }
            if (engineTemperature < AppConfig.ENGINE_TEMP_HEAT) {
                engineHeat = false;
            }
        };
    }

    public static boolean getEngineHeat() {
        return engineHeat;
    }

    public static boolean getEngineOverHeat() {
        return engineOverHeat;
    }
}
