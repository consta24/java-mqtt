import consta.spm.MqttBroker.datahandler.ParserCSV;
import consta.spm.MqttBroker.models.PubSubTopicModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserCSVTest {
    private static ParserCSV parserCSV;

    @BeforeAll
    public static void init() {
        parserCSV = new ParserCSV();
    }

    @Test
    public void parseTest() {
        PubSubTopicModel expected = new PubSubTopicModel("UNIT", Arrays.asList("15", "18", "20"));
        PubSubTopicModel actual = parserCSV.parse(new File("src/test/resources/UNIT_TEST_DATA.csv"));

        assertEquals(expected.getValues(), actual.getValues());
        assertEquals(expected.getName(), actual.getName());
    }
}
