package app.models;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "sensor")
@XmlAccessorType(XmlAccessType.FIELD)
public class Topic {
    @XmlAttribute
    private String name;

    private List<String> values;

    public Topic(String name, List<String> values) {
        this.name = name;
        this.values = values;
    }
    public Topic(){

    }

    public List<String> getValues() {
        return values;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s with: %s\n", name, values);
    }

}
