package com.example.calculator.logic;

import com.example.calculator.basic.ExtendedCalculator;

import java.util.Scanner;

public class ExtendedCircleCalculator extends CircleCalculator implements ExtendedCalculator {

    @Override
    public void calculateArea() throws Exception {
        System.out.println("Podaj promien");

        Scanner odczyt = new Scanner(System.in);
        int r = odczyt.nextInt();

        if (r > 0) {
            System.out.println("Pole wynosi :" + (3.14*r*r));
        } else {
            throw new Exception("PODAJ WARTOSC WIEKSZA OD 0");
        }
    }
}
