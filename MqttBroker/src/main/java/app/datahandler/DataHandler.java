package app.datahandler;

import app.models.Topic;
import app.threads.TopicThreadFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataHandler {

    private static final Logger LOGGER = LogManager.getLogger(DataHandler.class);
    List<Topic> topics = new ArrayList<>();
    public List<File> extractFiles(String dataPath) {
        try(Stream<Path> filesStream = Files.list(Paths.get(dataPath))){
            return filesStream
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }
    public List<Topic> parseData(List<File> files) {
        for(File file : files) {
            String fileExtension = file.getName().substring(file.getName().lastIndexOf('.'));
            topics.add(DataParserFactory.getParser(fileExtension).parse(file));
        }
        return topics;
    }

}
