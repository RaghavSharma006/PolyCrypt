package com.raghav.polycrypt;

import java.math.BigDecimal;
import java.util.Base64;

public class PolynomialSerializer {

    /**
     * Polynomial[] -> String
     */
    public static String serialize(Polynomial[] polynomials) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < polynomials.length; i++) {

            BigDecimal[] coefficients = polynomials[i].coefficients;

            for (int j = 0; j < coefficients.length; j++) {

                sb.append(coefficients[j].toPlainString());

                if (j != coefficients.length - 1)
                    sb.append(",");

            }

            if (i != polynomials.length - 1)
                sb.append("|");
        }

        return Base64.getEncoder()
                .encodeToString(sb.toString().getBytes());
    }

    /**
     * String -> Polynomial[]
     */
    public static Polynomial[] deserialize(String ciphertext) {

        String decoded = new String(
                Base64.getDecoder().decode(ciphertext)
        );

        String[] polynomialStrings = decoded.split("\\|");

        Polynomial[] polynomials = new Polynomial[polynomialStrings.length];

        for (int i = 0; i < polynomialStrings.length; i++) {

            String[] coeffs = polynomialStrings[i].split(",");

            Polynomial polynomial = new Polynomial(coeffs.length);

            for (int j = 0; j < coeffs.length; j++) {
                polynomial.coefficients[j] = new BigDecimal(coeffs[j]);
            }

            polynomials[i] = polynomial;
        }

        return polynomials;
    }

}