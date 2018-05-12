import java.io.*;
import java.util.Random;



public class Main {
    public static void main(String[] args) {
        ScatterSystem system = new ScatterSystem();
        ExtendedSystemCache cache = new ExtendedSystemCache();
        Random generator = new Random();


        for(int i =0; i<2; i++) {

            double[] input = new double[5];

            for (int j = 0; j < 5; j++)
                input[j] = generator.nextInt() ;

            Double output = cache.get(input);

            if (output == null) {
                output = system.makeOperation(input);
                cache.put(input, output);
            }
        }

        try
        {

            cache.exportCSV("Files/CSV/pierwsze.csv");
            cache.importCSV("Files/CSV/pierwsze.csv");

            File file = new File("Files/CSV/drugi.csv");
            cache.exportCSV(file);
            cache.importCSV(file);

            OutputStream out = new FileOutputStream("Files/CSV/trzeci.csv");
            cache.exportCSV(out);
            InputStream inputStream = new FileInputStream("Files/CSV/trzeci.csv");
            cache.importCSV(inputStream);

            cache.serialize("Files/CSV/firstSerialize.ser");
            cache.deserialize("Files/CSV/firstSerialize.ser");




        }
        catch (IOException event)
        {
            event.printStackTrace();
        }


    }
}
