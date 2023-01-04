package app.datahandler;

import app.models.Topic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import java.io.File;
import java.io.FileReader;

public class ParserXML implements DataParser {

    private static final Logger LOGGER = LogManager.getLogger(ParserXML.class);
    @Override
    public Topic parse(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(Topic.class);
            return (Topic) context.createUnmarshaller().unmarshal(new FileReader(String.valueOf(file.toPath())));
        } catch (Exception e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }
}
