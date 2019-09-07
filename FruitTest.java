import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FruitTest {
    public static void main(String[] args) throws Exception {

           
        Fruit f1 = new Fruit("Apple", "     Sunny Farm        ", 8.25);
        Fruit f2 = new Fruit("Banana", "    Dale Farm         ", 6.3);
        Fruit f3 = new Fruit("Strawberry", "Hertfordshire Farm", 12.7);
        Fruit f4 = new Fruit("Raspberry", " Lancs Farm        ", 0);
        Fruit f5 = new Fruit("Lychee", "    Dorset Farm       ", 0);
        Fruit f6 = new Fruit("Pear", "      Dover Farm        ", 5.85);
        Fruit f7 = new Fruit("Mango", "     Surrey Farm       ", 15.2);

    
        ArrayList<Fruit> fruits = new ArrayList<Fruit>();
        fruits.add(f1);
        fruits.add(f2);
        fruits.add(f3);
        fruits.add(f4);
        fruits.add(f5);
        fruits.add(f6);
        fruits.add(f7);

       
        FruitUtils.serializeToDisk(fruits);
      
        List<Fruit> fruits2 = FruitUtils.fromSerialized();
       
        testMatch(fruits, fruits2);
        FruitUtils.displayFruits(fruits2);
       
        FruitUtils.writeFruitsToFile(fruits, "fruits.txt");
       
        FileOutputStream fos = new FileOutputStream("test.txt");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        bw.write("Name,producer,price");
        bw.newLine();
        for (Fruit fruit : fruits) {
            bw.write(fruit.getName() + ", " + fruit.getProducer() + ", " + fruit.getPricePerKilo());
            bw.newLine();
        }
        bw.close();

        fruits2 = FruitUtils.parseFruitsFromCSV("test.txt");
        
        testMatch(fruits, fruits2);


    }

    private static void testMatch(List<Fruit> fruits, List<Fruit> fruits2) {
        boolean ok = true;
        
        for (int i = 0; i < fruits2.size(); i++) {
            Fruit fruit = fruits.get(i);
            Fruit fruit2 = fruits2.get(i);

            if (!(fruit.getName().equals(fruit2.getName()))
                    && fruit.getProducer().equals(fruit2.getProducer())
                    && fruit.getPricePerKilo() == fruit2.getPricePerKilo()) {
                ok = false;
            }
        }
        if (!ok) {
            System.out.println("Lists do not match");
        } else {
            System.out.println("Lists match");
        }

    }
}
