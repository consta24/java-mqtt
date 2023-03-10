import consta.spm.MqttBroker.datahandler.DataHandler;
import consta.spm.MqttBroker.models.PubSubTopicModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataHandlerTest {

    private static DataHandler dataHandler;

    @BeforeAll
    public static void init() {
        dataHandler = new DataHandler();
    }

    @Test
    public void extractFilesTest() {
        List<File> files = dataHandler.extractFiles("src/test/resources");
        assertEquals("UNIT_TEST_DATA.csv", files.get(0).getName());
        assertEquals("UNIT_TEST_DATA.xml", files.get(1).getName());
    }

    @Test
    public void parseDataTest() {
        List<PubSubTopicModel> pubSubTopicModels = dataHandler.parseData(dataHandler.extractFiles("src/test/resources"));

        assertEquals("UNIT", pubSubTopicModels.get(0).getName());
        assertEquals(Arrays.asList("15", "18", "20"), pubSubTopicModels.get(0).getValues());

        assertEquals("UNIT", pubSubTopicModels.get(1).getName());
        assertEquals(Arrays.asList("20", "18", "20", "16", "25"), pubSubTopicModels.get(1).getValues());
    }
}
