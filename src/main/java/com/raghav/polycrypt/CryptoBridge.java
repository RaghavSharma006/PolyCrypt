package com.raghav.polycrypt;

import java.util.ArrayList;

public class CryptoBridge {

    public String encrypt(ArrayList<String[]> input) {

        System.out.println("\n========== CryptoBridge.encrypt() ==========");

        System.out.println("Input Size : " + input.size());

        System.out.println("\n========== ORIGINAL INPUT ==========");

        for (int i = 0; i < input.size(); i++) {
            System.out.println(
                    "[" + i + "] " +
                            "Message = '" + input.get(i)[0] + "' (" + input.get(i)[0].length() + ")" +
                            " | Key = '" + input.get(i)[1] + "' (" + input.get(i)[1].length() + ")"
            );
        }

        System.out.println("--------------------------------");

        ArrayList<String[]> wrapped = new ArrayList<>();

        for (String[] pair : input) {

            String wrappedMessage = test.START + pair[0] + test.SUPER_END;

            wrapped.add(new String[]{
                    wrappedMessage,
                    pair[1]
            });

            System.out.println("Wrapped : " + wrappedMessage);
        }

        System.out.println("--------------------------------");
        System.out.println("Calling Encrypt.encrypt()");

        Encrypt encrypt = new Encrypt();

        ArrayList<Integer>[] xy = encrypt.encrypt(wrapped);

        System.out.println("--------------------------------");
        System.out.println("Encrypt.encrypt() finished");

        System.out.println("PointsX Size : " + xy[0].size());
        System.out.println("PointsY Size : " + xy[1].size());

        System.out.println("PointsX : " + xy[0]);
        System.out.println("PointsY : " + xy[1]);

        System.out.println("--------------------------------");
        System.out.println("Generating Blocks");

        Blocker blocker = new Blocker();

        ArrayList<ArrayList<Integer>[]> blocks =
                blocker.generateBlocks(xy, wrapped.size());

        System.out.println("Blocks Generated : " + blocks.size());

        for (int i = 0; i < blocks.size(); i++) {

            System.out.println(
                    "Block " + i +
                            " X=" + blocks.get(i)[0].size() +
                            " Y=" + blocks.get(i)[1].size()
            );
        }

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