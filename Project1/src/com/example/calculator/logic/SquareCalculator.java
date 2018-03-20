package com.example.calculator.logic;

import com.example.calculator.basic.Calculator;

import java.util.Scanner;

public class SquareCalculator implements Calculator {


    @Override
    public double calculatePerimeter() {

            System.out.println("Podaj dlugosc boku");
        Scanner odczyt = new Scanner(System.in);
        int a = odczyt.nextInt();


        if (a > 0)
            return 4*a;
        else {
            System.out.println("PODAJ LICZBE DODATNIA!!!");
            return -1;
        }
    }
}
