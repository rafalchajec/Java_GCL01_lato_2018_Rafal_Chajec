package com.example.calculator.logic;

import com.example.calculator.basic.Calculator;

import java.util.Scanner;

public class RectangleCalculator implements Calculator
{
    @Override
    public double calculatePerimeter() {

        System.out.println("Podaj wymiary prostokata");
        Scanner odczyt = new Scanner(System.in);
        int a = odczyt.nextInt();
        int b = odczyt.nextInt();

        if (a > 0 && b > 0)
            return 2*a + 2*b ;
        else {
            System.out.println("PODAJ LICZBY DODATNIA!!!");
            return -1;
        }
    }
}
