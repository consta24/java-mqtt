package consta.spm.MqttBroker.datahandler;

import consta.spm.MqttBroker.models.PubSubTopicModel;

import java.io.File;

public interface IDataParser {
    PubSubTopicModel parse(File file);
}
