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

        Polynomial polynomial = new Polynomial(polynomialSize);


        for(int i = 0; i < pointsX.size(); i++){
            Polynomial tempPolynomial = new Polynomial(1);
            tempPolynomial.coefficients[0]= BigDecimal.valueOf(1);

            for(int j=0;j<pointsX.size();j++){
                if(i==j) continue;
                Polynomial current= new Polynomial(2);
                current.coefficients[0] = BigDecimal.valueOf(-pointsX.get(j));
                current.coefficients[1] = BigDecimal.valueOf(1);
                tempPolynomial=multiply(tempPolynomial,current);
            }

            tempPolynomial = divideByConstant(
                    tempPolynomial,
                    getPolynomialValue(tempPolynomial, BigDecimal.valueOf(pointsX.get(i)))
            );
            tempPolynomial = multiplyByConstant(tempPolynomial, BigDecimal.valueOf(pointsY.get(i)));


            polynomial=add(polynomial,tempPolynomial);
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
