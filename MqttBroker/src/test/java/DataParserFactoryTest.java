import app.datahandler.DataParserFactory;
import app.datahandler.ParserCSV;
import app.datahandler.ParserXML;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataParserFactoryTest {

    @Test
    void getParserTest() {
        assertEquals(ParserXML.class, DataParserFactory.getParser(".xml").getClass());
        assertEquals(ParserCSV.class, DataParserFactory.getParser(".csv").getClass());
    }
}
