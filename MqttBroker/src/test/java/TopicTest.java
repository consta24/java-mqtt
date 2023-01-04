import app.models.Topic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TopicTest {

    static Topic topic;

    @BeforeAll
    static void setUpTopic() {
        topic = new Topic("name", Arrays.asList("1", "2", "3"));
    }
    @Test
    void testGetValues() {
        assertEquals(Arrays.asList("1", "2", "3"), topic.getValues());
    }
    @Test
    void testGetTopic() {
        assertEquals("name", topic.getName());
    }
}