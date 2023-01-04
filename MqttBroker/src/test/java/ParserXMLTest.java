import app.datahandler.ParserXML;
import app.models.Topic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ParserXMLTest {
    private static ParserXML parserXML;

    @BeforeAll
    static void init(){
        parserXML = new ParserXML();
    }

    @Test
    void parseTest() {
        Topic expected = new Topic("UNIT", Arrays.asList("20", "18", "20", "16", "25"));
        Topic actual = parserXML.parse(new File("testdata/UNIT_TEST_DATA.xml"));

        assertEquals(expected.getValues(), actual.getValues());
        assertEquals(expected.getName(), actual.getName());
    }
}
