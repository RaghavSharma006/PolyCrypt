package com.raghav.polycrypt;

import java.math.BigDecimal;

public class Test2 {

    public static void main(String[] args) {

        PolynomialGenerator generator = new PolynomialGenerator();

        Polynomial polynomial = new Polynomial(4);

        // x^3 - 6x^2 + 11x - 6
        polynomial.coefficients[0] = BigDecimal.valueOf(-6);
        polynomial.coefficients[1] = BigDecimal.valueOf(11);
        polynomial.coefficients[2] = BigDecimal.valueOf(-6);
        polynomial.coefficients[3] = BigDecimal.ONE;

        Polynomial quotient =
                generator.divideByLinear(polynomial, BigDecimal.valueOf(2));

        System.out.println("Quotient:");

        for (int i = 0; i < quotient.coefficients.length; i++) {
            System.out.println("x^" + i + " : " + quotient.coefficients[i]);
        }

        System.out.println();

        System.out.println("P(2) = " +
                generator.getPolynomialValue(polynomial, BigDecimal.valueOf(2)));

        System.out.println("Q(2) = " +
                generator.getPolynomialValue(quotient, BigDecimal.valueOf(2)));
    }
}