package app.datahandler;

import app.models.Topic;

import java.io.File;

public interface DataParser {
    Topic parse(File file);

}
