import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class ExtendedSystemSaveCacheTest {
    private ScatterSystem system;
    private ExtendedSystemCache extendedSystemCache;

    @Before
    public void beforeClass()
    {
        system = new ScatterSystem();
        extendedSystemCache = new ExtendedSystemCache();
    }

    @Test
    public void save() {
        Double output;
        double[] input = {3,56};
        output = extendedSystemCache.get(input);
        if (output == null) {
            output = system.makeOperation(input);
            extendedSystemCache.put(input, output);
        }

        try {
            extendedSystemCache.save("Files/CSV/Test.bin");
            extendedSystemCache.load("Files/CSV/Test.bin");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Output: "+output);
        System.out.println("Output after sav/lo: "+extendedSystemCache.get(input));
        Assert.assertEquals(output, extendedSystemCache.get(input));
    }

    @Test
    public void save1() {
        Double output;
        double[] input = {16,28};
        output = extendedSystemCache.get(input);
        if (output == null) {
            output = system.makeOperation(input);
            extendedSystemCache.put(input, output);
        }

        try {
            File file = new File("Files/CSV/Test.bin");
            extendedSystemCache.save(file);
            extendedSystemCache.load(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Output: "+output);
        System.out.println("Output after sav/lo: "+extendedSystemCache.get(input));
        Assert.assertEquals(output, extendedSystemCache.get(input));
    }

    @Test
    public void save2() {
    }
}