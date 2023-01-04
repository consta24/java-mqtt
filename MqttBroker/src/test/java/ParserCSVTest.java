import app.datahandler.ParserCSV;
import app.models.Topic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ParserCSVTest {
    private static ParserCSV parserCSV;

    @BeforeAll
    static void init(){
        parserCSV = new ParserCSV();
    }

    @Test
    void parseTest() {
        Topic expected = new Topic("UNIT", Arrays.asList("15", "18", "20"));
        Topic actual = parserCSV.parse(new File("testdata/UNIT_TEST_DATA.csv"));

        assertEquals(expected.getValues(), actual.getValues());
        assertEquals(expected.getName(), actual.getName());
    }
}
