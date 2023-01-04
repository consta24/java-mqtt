package consta.spm.MqttBroker.datahandler;

import consta.spm.MqttBroker.models.PubSubTopicModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import java.io.File;
import java.io.FileReader;

public class ParserXML implements IDataParser {

    private static final Logger LOGGER = LogManager.getLogger(ParserXML.class);

    @Override
    public PubSubTopicModel parse(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(PubSubTopicModel.class);
            return (PubSubTopicModel) context.createUnmarshaller().unmarshal(new FileReader(String.valueOf(file.toPath())));
        } catch (Exception e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }
}
