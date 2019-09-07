import java.io.Serializable;

public class Grocery implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String producer;

    public Grocery(String name, String producer) {
        this.name = name;
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public String getProducer() {
        return producer;
    }

    public String toString() {
        return String.format("Name: %s\nProducer: %s", name, producer);
    }
}
