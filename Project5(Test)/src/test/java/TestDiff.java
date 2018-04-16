import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDiff {
    BasicCalculator kalkulator=new BasicCalculator();
    @Test
    public void calculateDifference1() {

        double t1 = kalkulator.calculateDifference(4, 2);
        assertEquals(2, t1, 0);

    }
    @Test
    public void calculateDifference2() {


        double t2 = kalkulator.calculateDifference(0, 100);
        assertEquals(-100, t2, 0);

    }
    @Test
    public void calculateDifference3() {

        double t3 = kalkulator.calculateDifference(0, 0);
        assertEquals(0, t3, 0);
    }
}
