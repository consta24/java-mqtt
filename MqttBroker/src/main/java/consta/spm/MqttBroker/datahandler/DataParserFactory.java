package consta.spm.MqttBroker.datahandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static consta.spm.MqttBroker.AppConfig.EXTENSION_CSV;
import static consta.spm.MqttBroker.AppConfig.EXTENSION_XML;

public class DataParserFactory {

    private static final Map<String, IDataParser> factoryMap =
            Collections.unmodifiableMap(new HashMap<String, IDataParser>() {{
                put(EXTENSION_XML, new ParserXML());
                put(EXTENSION_CSV, new ParserCSV());
            }});

    public static IDataParser getParser(String fileExtension) {

        IDataParser parser = factoryMap.get(fileExtension);

        if (parser != null) {
            return parser;
        } else {
            throw new NullPointerException("Parser not found");
        }
    }
}
