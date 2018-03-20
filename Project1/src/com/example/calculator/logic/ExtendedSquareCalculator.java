package com.example.calculator.logic;

import com.example.calculator.basic.ExtendedCalculator;

import java.util.Scanner;

public class ExtendedSquareCalculator extends SquareCalculator implements ExtendedCalculator {

        @Override
        public void calculateArea() throws Exception  {

            System.out.println("Podaj dlugosc boku");
            Scanner odczyt = new Scanner(System.in);
            int a = odczyt.nextInt();

            if (a > 0)
                System.out.println("Pole wynosi :" + (a * a));
            else {
                throw new Exception("PODAJ WARTOSCI WIEKSZE OD 0");
            }
        }
}
