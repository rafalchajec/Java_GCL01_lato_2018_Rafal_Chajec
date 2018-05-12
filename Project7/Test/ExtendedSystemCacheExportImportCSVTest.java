import org.junit.Assert;
import org.junit.Before;
import java.io.*;

public class ExtendedSystemCacheExportImportCSVTest {
    private ScatterSystem system;
    private ExtendedSystemCache extendedSystemCache;

    @Before
    public void beforeClass()
    {
        system = new ScatterSystem();
        extendedSystemCache = new ExtendedSystemCache();
    }

    @org.junit.Test
    public void exportCSV() {
        Double output;
        double[] input = {2,56};
        output = extendedSystemCache.get(input);
        if (output == null) {
            output = system.makeOperation(input);
            extendedSystemCache.put(input, output);
        }

        try {
            extendedSystemCache.exportCSV("Files/CSV/pierwszyTest.csv");
            extendedSystemCache.importCSV("Files/CSV/pierwszyTest.csv");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Output: "+output);
        System.out.println("Output after ex/im: "+extendedSystemCache.get(input));
        Assert.assertEquals(output, extendedSystemCache.get(input));
    }

    @org.junit.Test
    public void exportCSV1() {
        Double output;
        double[] input = {3,14};
        output = extendedSystemCache.get(input);
        if (output == null) {
            output = system.makeOperation(input);
            extendedSystemCache.put(input, output);
        }

        try {
            File file = new File("Files/CSV/drugiTest.csv");
            extendedSystemCache.exportCSV(file);
            extendedSystemCache.importCSV(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Output: "+output);
        System.out.println("Output after ex/im: "+extendedSystemCache.get(input));
        Assert.assertEquals(output, extendedSystemCache.get(input));
    }

    @org.junit.Test
    public void exportCSV2() {
        Double output;
        double[] input = {15,66, 5};
        output = extendedSystemCache.get(input);
        if (output == null) {
            output = system.makeOperation(input);
            extendedSystemCache.put(input, output);
        }

        try {
            OutputStream outputStream = new FileOutputStream("Files/CSV/thirdTest.csv");
            extendedSystemCache.exportCSV(outputStream);
            InputStream inputStream = new FileInputStream("Files/CSV/thirdTest.csv");
            extendedSystemCache.importCSV(inputStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Output: "+output);
        System.out.println("Output after ex/im: "+extendedSystemCache.get(input));
        Assert.assertEquals(output, extendedSystemCache.get(input));
    }
}