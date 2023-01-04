package consta.spm.MqttBroker.datahandler;

import consta.spm.MqttBroker.models.PubSubTopicModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParserCSV implements IDataParser {

    private static final Logger LOGGER = LogManager.getLogger(ParserCSV.class);

    @Override
    public PubSubTopicModel parse(File file) {
        try (Stream<String> lines = Files.lines(file.toPath())) {
            List<String> topicData = lines
                    .map(line -> line.split(",(?![^{]*})"))
                    .flatMap(Arrays::stream)
                    .collect(Collectors.toList());
            return new PubSubTopicModel(getTopicName(file.getName()), topicData);
        } catch (IOException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    private String getTopicName(String fileName) {
        return fileName.substring(0, fileName.substring(0, fileName.lastIndexOf('_')).lastIndexOf('_'));
    }
}
