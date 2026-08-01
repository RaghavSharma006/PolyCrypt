package com.raghav.polycrypt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;

public class test {
    static int MAX=4;
    public static final char START      = '\uE000'; // Start of Message (SOM)
    public static final char END        = '\uE001'; // End of Block for this message (EOM)
    public static final char SUPER_END  = '\uE002'; // End of Message (no more blocks)

    public static void main(String[] args) throws Exception {

        Encrypt encrypt = new Encrypt();
        ArrayList<String[]> message = new ArrayList<>();
        System.out.println(START+""+END+""+SUPER_END);

        message.add(new String[]{START+"hello,Guddu"+SUPER_END, "12"});
        message.add(new String[]{START+"hello,Kellu"+END, "34"});

        ArrayList<Integer>[] hello=encrypt.encrypt(message);

        for (int i = 0; i < hello.length; i++){
            System.out.print(hello[i]);
            System.out.println(hello[i].size());
        }

        Blocker blocker = new Blocker();

        ArrayList<ArrayList<Integer>[]> hey=blocker.generateBlocks(hello,2);

        Polynomial[] polynomials=new Polynomial[hey.size()];

        for (int i = 0; i < hey.size(); i++) {

            polynomials[i]=new PolynomialGenerator().generatePolynomial(hey.get(i));

        }

        Decrypt decrypt = new Decrypt();
        System.out.println(decrypt.decrypt(polynomials,"12"));
        System.out.println("Done");

        for (int i = 0; i < polynomials.length; i++) {
            ArrayList<Integer> x = hey.get(i)[0];
            System.out.println(x);

            for (int j = 0; j < x.size(); j++) {

                BigDecimal value = new PolynomialGenerator()
                        .getPolynomialValue(polynomials[i],
                                BigDecimal.valueOf(x.get(j)));

                System.out.print((char) value.setScale(0, java.math.RoundingMode.HALF_UP).intValue());
            }

            System.out.println();
        }

    }

}
