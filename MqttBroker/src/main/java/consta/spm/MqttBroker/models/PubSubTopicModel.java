package consta.spm.MqttBroker.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "pubsubtopic")
@XmlAccessorType(XmlAccessType.FIELD)
public class PubSubTopicModel {

    @XmlAttribute
    private String name;

    private List<String> values;

    public PubSubTopicModel(String name, List<String> values) {
        this.name = name;
        this.values = values;
    }

    public PubSubTopicModel() {

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
