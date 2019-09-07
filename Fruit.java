public class Fruit extends Grocery {

    private double pricePerKilo; 

    public Fruit(String name, String producer, double pricePerKilo) {
        super(name, producer);
        this.pricePerKilo = pricePerKilo;
    }

    public Fruit(String name, String producer) {
        this(name, producer, -1);
    }

    public double getPricePerKilo() {
        return pricePerKilo;
    }

    public String toString() {
        return String.format("%s\nPricePerKilo: %.2f\n", super.toString(), pricePerKilo);
    }
}
