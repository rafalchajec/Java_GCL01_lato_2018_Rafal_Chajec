package com.example.calculator.logic;

import com.example.calculator.basic.ExtendedCalculator;

import java.util.Scanner;

public class ExtendedRectangleCalculator extends RectangleCalculator implements ExtendedCalculator {


        @Override
        public void calculateArea() throws Exception {

            System.out.println("Podaj wymiary prostokata");
            Scanner odczyt = new Scanner(System.in);

            int a = odczyt.nextInt();
            int b = odczyt.nextInt();

            if (a > 0 && b > 0) {
                System.out.println("Pole wynosi :" + (a * b));
            } else {
                throw new Exception("PODAJ WARTOSCI WIEKSZE OD 0");
            }
        }


}
