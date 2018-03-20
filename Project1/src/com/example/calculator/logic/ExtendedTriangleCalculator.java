package com.example.calculator.logic;

import com.example.calculator.basic.ExtendedCalculator;

import java.util.Scanner;

public class ExtendedTriangleCalculator extends TriangleCalculator implements ExtendedCalculator {
    @Override
    public void calculateArea() throws Exception  {

        System.out.println("Podaj dlugosc podstawy i wysokosc:");
        Scanner odczyt = new Scanner(System.in);
        int a = odczyt.nextInt();
        int h = odczyt.nextInt();
        if (a > 0&&h>0)
            System.out.println("Pole wynosi :" + ((a * h )/2));
        else {
            throw new Exception("PODAJ WARTOSCI WIEKSZE OD 0");
        }
    }
}
