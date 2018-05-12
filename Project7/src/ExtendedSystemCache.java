import java.io.*;
import java.util.HashMap;

public class ExtendedSystemCache extends SystemCache {
    void exportCSV(String path) throws IOException {

        File file = new File(path);
        OutputStream outputStream = new FileOutputStream(file);
        exportCSV(outputStream);
    }

    void exportCSV(File file) throws IOException {
        exportCSV(file.getPath());
    }

    void exportCSV(OutputStream stream) throws IOException {

        Writer writer = new OutputStreamWriter(stream, "UTF-8");

        for (Parameter key : cache.keySet()) {
            for (Double x : key.values) {
                writer.write(Double.toString(x));
                writer.write(",");
            }
            writer.write(";");
            writer.write(Double.toString(cache.get(key)));
            writer.write("\n");
        }

        writer.close();
        stream.close();
    }

    void importCSV(String path) throws IOException {

        File file = new File(path);
        if (!file.exists())
            throw new IOException("NO FILE!");

        InputStream inputStream = new FileInputStream(file);
        importCSV(inputStream);
    }

    void importCSV(File file) throws IOException {
        importCSV(file.getPath());
    }

    void importCSV(InputStream stream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        cache.clear();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] array = line.split(";");
            String[] array2 = array[0].split(",");
            double[] arrayDouble = new double[array2.length];

            for (int i = 0; i < array2.length; i++)
                arrayDouble[i] = Double.parseDouble(array2[i]);

            Parameter parameter = new Parameter(arrayDouble);
            cache.put(parameter, Double.parseDouble(array[1]));
        }

        stream.close();
    }

    void serialize(String path) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path);


        serialize(fileOutputStream);

        fileOutputStream.close();
    }

    void serialize ( File file ) throws IOException {
        serialize(file.getPath());
    }

    void serialize ( OutputStream stream ) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
        objectOutputStream.writeObject(cache);
        objectOutputStream.close();
        stream.close();
    }

    void deserialize( String path ) throws IOException {
        File file = new File(path);
        if (!file.exists())
            throw new IOException("NO FILE");

        FileInputStream fileInputStream = new FileInputStream(path);
        deserialize(fileInputStream);
    }

    void deserialize( File file ) throws IOException {
        deserialize(file.getPath());
    }

    void deserialize( InputStream stream ) throws IOException {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(stream);
            cache = (HashMap<Parameter, Double>) objectInputStream.readObject();

            objectInputStream.close();

            stream.close();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void save( String path ) throws IOException {
        File file = new File(path);
        OutputStream outputStream = new FileOutputStream(file);
        save(outputStream);
    }

    void save( File file ) throws IOException {
        save(file.getPath());
    }

    void save( OutputStream stream ) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(stream);

        for (Parameter key : cache.keySet()) {
            dataOutputStream.writeInt(key.values.length);
            for (Double x : key.values) {
                dataOutputStream.writeDouble(x);
            }

            dataOutputStream.writeDouble(cache.get(key));
            dataOutputStream.writeUTF("\n");
        }

    }

    void load( String path ) throws IOException {
        File file = new File(path);
        if (!file.exists())
            throw new IOException("NO FILE");
        InputStream inputStream = new FileInputStream(file);
        load(inputStream);
    }

    void load( File file ) throws IOException {
        load(file.getPath());
    }

    void load( InputStream stream ) throws IOException {

        cache.clear();

        DataInputStream dataInputStream = new DataInputStream(stream);

        try {
            while (true) {
                int number = dataInputStream.readInt();
                double[] tabDouble = new double[number];
                for (int i = 0; i <  number; i++)
                     tabDouble[i] = dataInputStream.readDouble();
                Parameter parameter = new Parameter(tabDouble);

                cache.put(parameter, dataInputStream.readDouble());
                dataInputStream.readUTF();
            }
        }
        catch (EOFException e) {
            e.printStackTrace();
        }
        dataInputStream.close();
    }

    void safe(String path) throws IOException
    {
        File file = new File(path);
        OutputStream outputStream = new FileOutputStream(file);

        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        dataOutputStream.writeInt(cache.keySet().size());
        dataOutputStream.writeInt(0);

        for (Parameter key : cache.keySet()) {
            dataOutputStream.writeInt(key.values.length);
            for (Double x : key.values) {
                dataOutputStream.writeDouble(x);
            }

            dataOutputStream.writeDouble(cache.get(key));
        }
    }
}