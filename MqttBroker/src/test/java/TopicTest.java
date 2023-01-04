import consta.spm.MqttBroker.models.PubSubTopicModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TopicTest {

    static PubSubTopicModel pubSubTopicModel;

    @BeforeAll
    static void setUpTopic() {
        pubSubTopicModel = new PubSubTopicModel("name", Arrays.asList("1", "2", "3"));
    }
    @Test
    void testGetValues() {
        assertEquals(Arrays.asList("1", "2", "3"), pubSubTopicModel.getValues());
    }
    @Test
    void testGetTopic() {
        assertEquals("name", pubSubTopicModel.getName());
    }
}