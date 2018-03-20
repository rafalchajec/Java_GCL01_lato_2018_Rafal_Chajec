package com.example.calculator.logic;

import com.example.calculator.basic.Calculator;

import java.util.Scanner;

public class CircleCalculator implements Calculator
{
    @Override
    public double calculatePerimeter() {

        System.out.println("Podaj promien");

        Scanner odczyt = new Scanner(System.in);
        int r = odczyt.nextInt();

        if (r > 0)
            return 2*(3.14)*r;
        else {
            System.out.println("PODAJ LICZBE DODATNIA!!!");
            return -1;
        }
    }
}
