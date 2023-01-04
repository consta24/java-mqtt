import consta.spm.MqttBroker.datahandler.DataParserFactory;
import consta.spm.MqttBroker.datahandler.ParserCSV;
import consta.spm.MqttBroker.datahandler.ParserXML;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IDataParserFactoryTest {

    @Test
    void getParserTest() {
        assertEquals(ParserXML.class, DataParserFactory.getParser(".xml").getClass());
        assertEquals(ParserCSV.class, DataParserFactory.getParser(".csv").getClass());
    }
}
