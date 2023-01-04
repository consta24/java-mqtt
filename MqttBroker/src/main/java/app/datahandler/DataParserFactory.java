package app.datahandler;

import java.util.*;

import static app.AppConfig.EXTENSION_CSV;
import static app.AppConfig.EXTENSION_XML;

public class DataParserFactory {

    private static final Map<String, DataParser> factoryMap =
            Collections.unmodifiableMap(new HashMap<String, DataParser>() {{
               put(EXTENSION_XML, new ParserXML());
               put(EXTENSION_CSV, new ParserCSV());
    }});

    public static DataParser getParser(String fileExtension){

        DataParser parser = factoryMap.get(fileExtension);

        if(parser != null) {
            return parser;
        } else {
            throw new NullPointerException("Parser not found");
        }
    }
}
