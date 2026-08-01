package com.raghav.polycrypt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@SpringBootApplication
public class PolycryptApplication {

	public static void main(String[] args) throws Exception {

		Encrypt encrypt = new Encrypt();
		ArrayList<String[]> message = new ArrayList<>();

		message.add(new String[]{"1234567890!@#$%^&*()_+qwertyuiop", "Raghav"});

		ArrayList<Integer>[] hello=encrypt.encrypt(message);
		hello[0]=new ArrayList<>();

		for(int i=0;i<hello[1].size();i++){
			hello[0].add(i+1000);
		}

		for (int i = 0; i < hello.length; i++){
			System.out.print(hello[i]);
			System.out.println(hello[i].size());
		}


		Polynomial answer=new PolynomialGenerator().generatePolynomial(hello);

		for (int l = 0; l < hello[0].size(); l++) {

			BigDecimal b = new PolynomialGenerator()
					.getPolynomialValue(answer, BigDecimal.valueOf(hello[0].get(l)));

			System.out.print((char) b.intValue());
		}
		System.out.println();
		for (int l = 0; l < hello[0].size(); l++) {

			BigDecimal b = new PolynomialGenerator()
					.getPolynomialValue(answer, BigDecimal.valueOf(hello[0].get(l)));

			System.out.print((char) b.setScale(0, java.math.RoundingMode.HALF_UP).intValue());
		}
		System.out.println();
		System.out.println("Hello World");
		SpringApplication.run(PolycryptApplication.class, args);
	}

}
