import static java.lang.Math.*;

public class BasicCalculator {

    public double calculateSum(double a, double b) {

        return a + b;
    }


    public double calculateDifference(double a, double b) {

        return  a - b;
    }

    public double calculateMultiplication(double a, double b) {

        return a * b;

    }

    public double calculateDivision(double a, double b) throws IllegalArgumentException{

            if (b == 0) throw new IllegalArgumentException("Dzielenie przez zero!");



        return  a / b;
    }

    public double calculatePow(double a, double power) {



        return pow(a, power);

    }

    public double calculateSqrt(double a) throws IllegalArgumentException{

            if (a < 0) throw new IllegalArgumentException("Ujemna wartosc podczas pierwiastkowania!");

        return sqrt(a);
    }

}