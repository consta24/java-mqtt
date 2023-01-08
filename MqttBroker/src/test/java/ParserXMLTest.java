import consta.spm.MqttBroker.datahandler.ParserXML;
import consta.spm.MqttBroker.models.PubSubTopicModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserXMLTest {
    private static ParserXML parserXML;

    @BeforeAll
    public static void init() {
        parserXML = new ParserXML();
    }

    @Test
    public void parseTest() {
        PubSubTopicModel expected = new PubSubTopicModel("UNIT", Arrays.asList("20", "18", "20", "16", "25"));
        PubSubTopicModel actual = parserXML.parse(new File("src/test/resources/UNIT_TEST_DATA.xml"));

        assertEquals(expected.getValues(), actual.getValues());
        assertEquals(expected.getName(), actual.getName());
    }
}
