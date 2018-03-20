package com.example.calculator.logic;

import com.example.calculator.basic.Calculator;

import java.util.Scanner;

public class TriangleCalculator implements Calculator
{
    @Override
    public double calculatePerimeter() {

        System.out.println("Podaj dlugosc boku trojkata rownobocznego");

        Scanner odczyt = new Scanner(System.in);
        int a = odczyt.nextInt();


        if (a > 0)
            return 3*a;
        else {
            System.out.println("PODAJ LICZBE DODATNIA!!!");
            return -1;
        }
    }
}
