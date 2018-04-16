import java.util.Scanner;

public class main{
    public static void main(String[] args) {


        System.out.println("Jeśli chcesz liczyć proste równania matemetycznie wybierz(1) \n Jeśli chcesz policzyć pole jakiejś figury wybierz (2):");

        BasicCalculator kalkulator = new BasicCalculator();
        FieldCalculator kalkulator1 = new FieldCalculator();

        Scanner scanner = new Scanner(System.in);
        double a;
        double b;
        int liczba = scanner.nextInt();
        switch (liczba) {
            case 1:
                System.out.println(" \n Suma (1) \n Różnica (2) \n Mnożenie (3) \n Dzielenie (4)\n Potegowanie(5) \n Pierwiastek 2 stopnia (6) \n :");
                int x = scanner.nextInt();
                switch (x) {
                    case 1:


                        System.out.println("Podaj dwie liczby do dodania (a+b):");


                        a = scanner.nextDouble();
                        b = scanner.nextDouble();

                        System.out.println("Wynik wynosi:");
                        System.out.println( kalkulator.calculateSum(a, b));

                        break;
                    case 2:

                        System.out.println("Podaj dwie liczby do odejmowania (a-b):");


                        a = scanner.nextDouble();
                        b = scanner.nextDouble();

                        System.out.println("Wynik wynosi:");
                        System.out.println(  kalkulator.calculateDifference(a, b));
                        break;
                    case 3:


                        System.out.println("Podaj dwie liczby do pomnożenia (a*b):");


                        a = scanner.nextDouble();
                        b = scanner.nextDouble();
                        System.out.println("Wynik wynosi:");
                        System.out.println(  kalkulator.calculateMultiplication(a, b));

                        break;
                    case 4:

                        System.out.println("Podaj dwie liczby do podzielenia (a/b):");


                        a = scanner.nextDouble();
                        b = scanner.nextDouble();
                        System.out.println("Wynik wynosi:");
                        System.out.println(  kalkulator.calculateDivision(a, b));
                        break;
                    case 5:
                        System.out.println("Najpierw podaj liczbę a później do jakiej potegi chcesz ja podniesc :");


                        a = scanner.nextDouble();
                        b = scanner.nextDouble();

                        System.out.println("WYnik wynosi:");
                        System.out.println(  kalkulator.calculatePow(a, b));
                        break;
                    case 6:
                        System.out.println("Podaj liczbę która chcesz pierwiastkowac do 2 stopnia:");


                        a = scanner.nextDouble();
                        System.out.println("Wynik wynosi:");
                        System.out.println(  kalkulator.calculateSqrt(a));
                }
                break;

            case 2:
                System.out.println("POLE \n Kwadratu (1) \n Prostokata (2) \n Trojkata (3) \n Koła (4)\n):");
                int y = scanner.nextInt();
                switch (y) {
                    case 1:

                        System.out.println("Podaj długość boku kwadratu:");
                        a = scanner.nextDouble();
                        System.out.println("Wynik wynosi:");
                        System.out.println(  kalkulator1.calculateSquare(a));

                        break;
                    case 2:
                        System.out.println("Podaj długości boków prostokąta a i b:");


                        a = scanner.nextDouble();
                        b = scanner.nextDouble();

                        System.out.println("Wynik wynosi:");
                        System.out.println(   kalkulator1.calculateRectangle(a, b));
                        break;
                    case 3:
                        System.out.println("Podaj długośc podstawy trójkąta (a) oraz jego wysokośc (h):");


                        a = scanner.nextDouble();
                        b = scanner.nextDouble();
                        System.out.println("Wynik wynosi:");
                        System.out.println(  kalkulator1.calculateTriangle(a, b));
                        break;
                    case 4:

                        System.out.println("Podaj długość promienia koła:");


                        a = scanner.nextDouble();

                        System.out.println("Wynik wynosi:");
                        System.out.println(  kalkulator1.calculateCircle(a));

                        break;

                }
                break;

            default:
                System.out.println("Podaj liczbe 1 albo 2 !!!!!!");
        }
    }



}
