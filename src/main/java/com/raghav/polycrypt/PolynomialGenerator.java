package com.raghav.polycrypt;

import java.math.BigDecimal;
import java.util.ArrayList;
class Polynomial{
    BigDecimal[] coefficients;
        Polynomial(int size) {
            coefficients = new BigDecimal[size];
            for (int i = 0; i < size; i++) {
                coefficients[i] = BigDecimal.ZERO;
            }
        }
}

public class PolynomialGenerator {

    int polynomialSize;

    Polynomial generatePolynomial(ArrayList<Integer>[] coefficients){

        ArrayList<Integer> pointsX= coefficients[0];
        ArrayList<Integer> pointsY= coefficients[1];

        polynomialSize=pointsX.size();

        Polynomial bigAssPolynomial = new Polynomial(1);
        bigAssPolynomial.coefficients[0] = BigDecimal.ONE;

        for (Integer x : pointsX) {
            Polynomial temp = new Polynomial(2);
            temp.coefficients[0] = BigDecimal.valueOf(-x);
            temp.coefficients[1] = BigDecimal.ONE;
            bigAssPolynomial = multiply(bigAssPolynomial, temp);
        }

        BigDecimal[] denominator = new BigDecimal[pointsX.size()];
        Polynomial[] numerators = new Polynomial[pointsX.size()];

        for (int i = 0; i < pointsX.size(); i++) {

            numerators[i] = divideByLinear(
                    bigAssPolynomial,
                    BigDecimal.valueOf(pointsX.get(i))
            );

            denominator[i] =getPolynomialValue(numerators[i], BigDecimal.valueOf(pointsX.get(i))
            );
        }


        Polynomial polynomial = new Polynomial(polynomialSize);

        for(int i = 0; i < pointsX.size(); i++){
            Polynomial temp= divideByConstant(numerators[i],denominator[i]);
            temp=multiplyByConstant(temp, BigDecimal.valueOf(pointsY.get(i)));
            polynomial=add(polynomial,temp);
        }

        return polynomial;
    }

    Polynomial add(Polynomial a, Polynomial b){
       Polynomial result = new Polynomial(Math.max(a.coefficients.length,b.coefficients.length));
       int min=Math.min(a.coefficients.length,b.coefficients.length);
       for (int i = 0; i <min; i++){
           result.coefficients[i] = a.coefficients[i].add(b.coefficients[i]);
       }
       for (int i=min; i<a.coefficients.length; i++){
           result.coefficients[i] = a.coefficients[i];
       }
       for (int i=min; i<b.coefficients.length; i++){
           result.coefficients[i] = b.coefficients[i];
       }
       return result;
    }

    Polynomial multiply(Polynomial a, Polynomial b) {
        BigDecimal[] result = new BigDecimal[a.coefficients.length + b.coefficients.length - 1];

        for (int i = 0; i < result.length; i++) {
            result[i] = BigDecimal.ZERO;
        }

        for (int i = 0; i < a.coefficients.length; i++) {
            for (int j = 0; j < b.coefficients.length; j++) {
                result[i + j] = result[i + j].add(
                        a.coefficients[i].multiply(b.coefficients[j])
                );
            }
        }

        Polynomial ans = new Polynomial(result.length);
        ans.coefficients = result;

        return ans;
    }

    Polynomial divideByLinear(Polynomial polynomial, BigDecimal root) {

        BigDecimal[] a = polynomial.coefficients;
        BigDecimal[] quotient = new BigDecimal[a.length - 1];

        // Highest degree coefficient
        quotient[quotient.length - 1] = a[a.length - 1];

        // Synthetic division
        for (int i = quotient.length - 2; i >= 0; i--) {
            quotient[i] = a[i + 1].add(
                    quotient[i + 1].multiply(root)
            );
        }

        // Optional remainder (should be ZERO for your master polynomial)
        BigDecimal remainder = a[0].add(
                quotient[0].multiply(root)
        );

        Polynomial ans = new Polynomial(quotient.length);
        ans.coefficients = quotient;

        return ans;
    }

    Polynomial multiplyByConstant(Polynomial a, BigDecimal c){


        for (int i = 0; i < a.coefficients.length; i++) {
            a.coefficients[i] =
                    a.coefficients[i].multiply(c);
        }

        return a;
    }

    Polynomial divideByConstant(Polynomial a, BigDecimal c){

        for (int i = 0; i < a.coefficients.length; i++) {
            a.coefficients[i] =
                    a.coefficients[i].divide(
                            c,
                            100,
                            java.math.RoundingMode.HALF_UP
                    );
        }
        return a;
    }

    BigDecimal getPolynomialValue(Polynomial polynomial,BigDecimal forValue){

        int len=polynomial.coefficients.length;
        BigDecimal answer=polynomial.coefficients[len-1];
        for(int i = len-2; i>=0; i--){
            answer =
                    forValue.multiply(answer)
                            .add(polynomial.coefficients[i]);
        }
        return answer;
    }
}
