import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ExtendedSystemSerializeCacheTest {
    private ScatterSystem system;
    private ExtendedSystemCache extendedSystemCache;

    @Before
    public void beforeClass()
    {
        system = new ScatterSystem();
        extendedSystemCache = new ExtendedSystemCache();
    }

    @Test
    public void serialize1() {
        Double output;
        double[] input = {3,56};
        output = extendedSystemCache.get(input);
        if (output == null) {
            output = system.makeOperation(input);
            extendedSystemCache.put(input, output);
        }

        try {
            extendedSystemCache.serialize("Files/CSV/firstSerTest.ser");
            extendedSystemCache.deserialize("Files/CSV/firstSerTest.ser");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Output: "+output);
        System.out.println("Output after ser/de: "+extendedSystemCache.get(input));
        Assert.assertEquals(output, extendedSystemCache.get(input));
    }

    @Test
    public void serialize2() {
        Double output;
        double[] input = {5,8,9,4};
        output = extendedSystemCache.get(input);
        if (output == null) {
            output = system.makeOperation(input);
            extendedSystemCache.put(input, output);
        }

        try {
            File file = new File("Files/CSV/secondSerTest.ser");
            extendedSystemCache.serialize(file);
            extendedSystemCache.deserialize(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Output: "+output);
        System.out.println("Output after ser/de: "+extendedSystemCache.get(input));
        Assert.assertEquals(output, extendedSystemCache.get(input));
    }

    @Test
    public void serialize3() {
        Double output;
        double[] input = {53,81,9,42};
        output = extendedSystemCache.get(input);
        if (output == null) {
            output = system.makeOperation(input);
            extendedSystemCache.put(input, output);
        }

        try {
            OutputStream outputStream = new FileOutputStream("Files/CSV/thirdSer.ser");
            extendedSystemCache.serialize(outputStream);
            InputStream inputStream = new FileInputStream("Files/CSV/thirdSer.ser");
            extendedSystemCache.deserialize(inputStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Output: "+output);
        System.out.println("Output after ser/de: "+extendedSystemCache.get(input));
        Assert.assertEquals(output, extendedSystemCache.get(input));
    }
}