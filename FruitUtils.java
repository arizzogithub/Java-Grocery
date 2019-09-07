import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FruitUtils {

    private static final String file_1 = "fruits.ser";
    private static final String file_2 = "fruits.txt";

    public FruitUtils() {
    }

    public static void displayFruits(List<Fruit> fruitList) {
        for (Fruit fruit : fruitList) {
            System.out.println(fruit.toString());
        }
    }

    public static ArrayList<Fruit> parseFruitsFromCSV(String file) {
        ArrayList<Fruit> fruits = new ArrayList<Fruit>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            br.readLine(); 
            while ((line = br.readLine()) != null) {
                Fruit fruit = parseFruit(line);
                fruits.add(fruit);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("File " + file + " does not exist.");
        }

        return fruits;
    }

    private static Fruit parseFruit(String line) {
        String[] details = line.split(", ");

        String name = details[0];
        String producer = details[1];
        double pricePerKilo = 0;
        if (details.length > 2) {
            pricePerKilo = Double.parseDouble(details[2]);
        }
        return new Fruit(name, producer, pricePerKilo);
    }

    public static void main(String[] args) {
        ArrayList<Fruit> fruits =
                FruitUtils.parseFruitsFromCSV("fruits.csv");
        displayFruits(fruits);
        writeFruitsToFile(fruits, file_2);
        serializeToDisk(fruits);
        fruits = fromSerialized();
        displayFruits(fruits);
    }

    public static void writeFruitsToFile(ArrayList<Fruit> fruits, String filename) {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter (new FileOutputStream(filename), "UTF-8"));
            for (Fruit f : fruits) {
                out.write(f.toString() + "\n");
            }
            out.close();
        } catch (Exception e) {
            System.err.println("error writing to file " + filename + ". " + e.getLocalizedMessage());
        }
    }

    public static void serializeToDisk(ArrayList<Fruit> fruits) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file_1);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(fruits);
            fos.close();
            oos.close();
        } catch (IOException e) {
            System.err.println("Unable to serialize to disk");
        }
    }

    public static ArrayList<Fruit> fromSerialized() {
        ArrayList<Fruit> fruits = new ArrayList<Fruit>();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file_1));
            fruits = (ArrayList<Fruit>) in.readObject();
            in.close();
        } catch (Exception e) {
            System.err.println("Unable to read from serialized file");
        }

        return fruits;
    }
}
