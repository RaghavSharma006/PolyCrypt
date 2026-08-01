package com.raghav.polycrypt;

import org.unbescape.json.JsonEscapeType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Decrypt {

    int messageLength=0;

    String decrypt(Polynomial[] polynomials,String key){


        StringBuilder result=new StringBuilder();
        System.out.println("Number of polynomials: "+polynomials.length);

        messageLength=getMessageLength(polynomials[0],key.charAt(0));

        System.out.println("Message length: "+messageLength);
        messageLength*= polynomials.length;
        System.out.println("Message length: "+messageLength);

        char[] keyChars=key.toCharArray();

        int counter=0;
        int keyRepeats=0;

        l1:
        for (int i = 0; i <polynomials.length; i++ ) {
            int perPolyMessage=messageLength/polynomials.length;


            for(int j=0;j<perPolyMessage;j++){

                if(counter==keyChars.length) {

                    counter=0;
                    keyRepeats++;
                }

                int x=getPointX(polynomials[i],keyChars[counter],counter,keyRepeats,key.length());
                counter++;

                if(x==perPolyMessage) continue;
                if(x==test.SUPER_END) break l1;
                result.append((char)x);


            }
        }
        System.out.println(result.toString()+"ENd");
        return result.toString();
    }

    int getMessageLength(Polynomial polynomial,char keyChar){
        PolynomialGenerator polynomialGenerator=new PolynomialGenerator();
        int a=generateHash(keyChar + "" + 0 + "" + 0);
        BigDecimal value=polynomialGenerator.getPolynomialValue(polynomial, BigDecimal.valueOf(a));
        return value.setScale(0, RoundingMode.HALF_UP).intValue();
    }

    int getPointX(Polynomial polynomial,
                  char keyChar,
                  int index,
                  int repeat,
                  int keyLength) {

        PolynomialGenerator polynomialGenerator = new PolynomialGenerator();

        int x = Math.abs(generateHash(keyChar + "" + index + "" + repeat));

        int globalIndex = repeat * keyLength + index;

        if ((globalIndex & 1) == 1) {
            x = -x;
        }

        BigDecimal value = polynomialGenerator.getPolynomialValue(
                polynomial,
                BigDecimal.valueOf(x));

        return value.setScale(0, RoundingMode.HALF_UP).intValue();
    }





    int generateHash(String message) {
        return message.hashCode();
    }
}
