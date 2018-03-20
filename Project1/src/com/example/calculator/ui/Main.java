package com.example.calculator.ui;

import com.example.calculator.basic.Calculator;
import com.example.calculator.basic.ExtendedCalculator;
import com.example.calculator.logic.*;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {
        System.out.println("Jeśli chcesz policzyć obwód jakiejś figury wybierz(1) \n Jeśli chcesz policzyć pole jakiejś figury wybierz (2):");

        Calculator kalkulator;


        ExtendedCalculator kalkulator1;
        Scanner podaj = new Scanner(System.in);

        int liczba = podaj.nextInt();
        switch (liczba) {
            case 1:
                System.out.println("OBWÓD \n Kwadratu (1) \n Prostokata (2) \n Trojkata (3) \n Koła (4)\n):");
                int x = podaj.nextInt();
                switch (x) {
                    case 1:

                        kalkulator = new SquareCalculator();
                        System.out.println("OBWÓD WYNOSI:" + kalkulator.calculatePerimeter());


                        break;
                    case 2:

                        kalkulator = new RectangleCalculator();
                        System.out.println("OBWÓD WYNOSI:" + kalkulator.calculatePerimeter());

                        break;
                    case 3:

                        kalkulator = new TriangleCalculator();
                        System.out.println("OBWÓD WYNOSI:" + kalkulator.calculatePerimeter());

                        break;
                    case 4:
                        kalkulator = new CircleCalculator();
                        System.out.println("OBWÓD WYNOSI:" + kalkulator.calculatePerimeter());

                        break;
                }
                break;

            case 2:
                System.out.println("POLE \n Kwadratu (1) \n Prostokata (2) \n Trojkata (3) \n Koła (4)\n):");
                int y = podaj.nextInt();
                switch (y) {
                    case 1:

                        kalkulator1 = new ExtendedSquareCalculator();
                        kalkulator1.calculateArea();

                        break;
                    case 2:
                        kalkulator1 = new ExtendedRectangleCalculator();
                        kalkulator1.calculateArea();


                        break;
                    case 3:
                        kalkulator1 = new ExtendedTriangleCalculator();
                        kalkulator1.calculateArea();

                        break;
                    case 4:
                        kalkulator1 = new ExtendedCircleCalculator();
                        kalkulator1.calculateArea();

                        break;

                }
                break;

            default:
                System.out.println("Podaj liczbe 1 albo 2 !!!!!!");
        }
    }
}
