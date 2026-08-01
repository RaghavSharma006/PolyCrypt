package com.raghav.polycrypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class PolynomialSerializer {

    /**
     * Polynomial[] -> Compressed Base64 String
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

        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            GZIPOutputStream gzip = new GZIPOutputStream(baos);

            gzip.write(sb.toString().getBytes(StandardCharsets.UTF_8));

            gzip.close();

            return Base64.getEncoder().encodeToString(baos.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Compressed Base64 String -> Polynomial[]
     */
    public static Polynomial[] deserialize(String ciphertext) {

        String decoded;

        try {

            byte[] compressed = Base64.getDecoder().decode(ciphertext);

            GZIPInputStream gzip =
                    new GZIPInputStream(new ByteArrayInputStream(compressed));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int len;

            while ((len = gzip.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            gzip.close();

            decoded = baos.toString(StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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