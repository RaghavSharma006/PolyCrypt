package com.raghav.polycrypt;

import java.util.ArrayList;

public class CryptoBridge {

    public String encrypt(ArrayList<String[]> input) {

        System.out.println("\n========== CryptoBridge.encrypt() ==========");

        System.out.println("Original Input:");

        for (int i = 0; i < input.size(); i++) {
            System.out.println(
                    "[" + i + "] Message = " + input.get(i)[0]
                            + " | Key = " + input.get(i)[1]
            );
        }

        System.out.println("--------------------------------");

        ArrayList<String[]> wrapped = new ArrayList<>();

        for (String[] pair : input) {

            String wrappedMessage = test.START + pair[0] + test.SUPER_END;

            System.out.println("Wrapping:");
            System.out.println(pair[0]);
            System.out.println("->");
            System.out.println(wrappedMessage);

            wrapped.add(new String[]{
                    wrappedMessage,
                    pair[1]
            });
        }

        System.out.println("--------------------------------");
        System.out.println("Calling Encrypt.encrypt()");

        Encrypt encrypt = new Encrypt();

        ArrayList<Integer>[] xy = encrypt.encrypt(wrapped);

        System.out.println("Encrypt.encrypt() finished");

        System.out.println("--------------------------------");
        System.out.println("Generating Blocks");

        Blocker blocker = new Blocker();

        ArrayList<ArrayList<Integer>[]> blocks =
                blocker.generateBlocks(xy, wrapped.size());

        System.out.println("Blocks Generated : " + blocks.size());

        Polynomial[] polynomials = new Polynomial[blocks.size()];

        PolynomialGenerator generator = new PolynomialGenerator();

        for (int i = 0; i < blocks.size(); i++) {

            System.out.println("--------------------------------");
            System.out.println("Generating Polynomial " + (i + 1));

            polynomials[i] =
                    generator.generatePolynomial(blocks.get(i));
        }

        System.out.println("--------------------------------");
        System.out.println("Serializing Polynomials");

        String cipher = PolynomialSerializer.serialize(polynomials);

        System.out.println("Serialization Complete");

        System.out.println("--------------------------------");
        System.out.println("Ciphertext:");

        System.out.println(cipher);

        System.out.println("========== END CryptoBridge.encrypt() ==========\n");

        return cipher;
    }

    public String decrypt(String ciphertext, String key) {

        System.out.println("\n========== CryptoBridge.decrypt() ==========");

        System.out.println("Deserializing Ciphertext");

        Polynomial[] polynomials =
                PolynomialSerializer.deserialize(ciphertext);

        System.out.println("Total Polynomials : " + polynomials.length);

        System.out.println("--------------------------------");
        System.out.println("Calling Decrypt.decrypt()");

        Decrypt decrypt = new Decrypt();

        String message = decrypt.decrypt(polynomials, key);

        System.out.println("--------------------------------");
        System.out.println("Recovered Message:");

        System.out.println(message);

        System.out.println("========== END CryptoBridge.decrypt() ==========\n");

        return message;
    }
}