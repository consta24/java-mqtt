import consta.spm.MqttBroker.models.PubSubTopicModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TopicTest {

    static PubSubTopicModel pubSubTopicModel;

    @BeforeAll
    public static void init() {
        pubSubTopicModel = new PubSubTopicModel("name", Arrays.asList("1", "2", "3"));
    }

    @Test
    public void testGetValues() {
        assertEquals(Arrays.asList("1", "2", "3"), pubSubTopicModel.getValues());
    }

    @Test
    public void testGetTopic() {
        assertEquals("name", pubSubTopicModel.getName());
    }
}