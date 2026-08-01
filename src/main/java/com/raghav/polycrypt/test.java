package com.raghav.polycrypt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;

public class test {
    static int MAX=10;
    public static final char START      = '\uE000'; // Start of Message (SOM)
    public static final char END        = '\uE001'; // End of Block for this message (EOM)
    public static final char SUPER_END  = '\uE002'; // End of Message (no more blocks)

    public static void main(String[] args) throws Exception {

        Encrypt encrypt = new Encrypt();
        ArrayList<String[]> message = new ArrayList<>();
        System.out.println(START+""+END+""+SUPER_END);

        message.add(new String[]{START+"Ohhhhh!! SO youve Guessed Dukkar. Haha It is funny isnt it. Wondering how much tries it took u to figure it out. BTW The reason this website exists is \"Happpppy Friendship DAY\" After HARSH I only see a true friend in you and that too this early hehe. Dosen't This website looks sick . Mera gift??? Baki Lovvvvvve youuuuuuu and Sorry but I wanted to wish SRU too. I know its not right but ya. Hope you'll understand your boy!"+SUPER_END, "Dkkar"});
        message.add(new String[]{START+"Moti haha so predictable isnt it. Kessi h website Made my own algorithm . Teri summer intership project se tuh better hi hoga willl seeee."+END, "Moti"});
        message.add(new String[]{START+"Harsh! Meri Degreee itni bhi nhi Bekar HAHAH . Happpy Friendship dayyy."+END, "Harsh"});
        message.add(new String[]{START+"Oh! You still remember the name I gave u once. Hoping youre reading this and its just not some random bytes who never got opened by her master.Ik things went downhill between us and u lost kellu bhi cause of me. Sorry dil se but I promise I never said anything bad to her about u ever. Goooood luck for your AFCAT Wanna hear uuuur flying high and fasssst. Sorry I shouldnt have came to your life ik Im miserable. Baki chilll Ive forgiven U long ago and I just miss that bak bak and energetic but ya Sorry will not come back Last message it is.Ya I still feel what u did kisi or reason se tha. U choose to sarifice juh galat tha"+END, "Guddu"});
        message.add(new String[]{START+"Shivu not that hard to guess . Is it? IDK I mean if youre reading this youve already figured it out. BTW frontend is AI looks sick isnt it. BTW Happpy friendship day. And gift select krle","Shiv"});
        message.add(new String[]{START+"Piggy! Kessa h. Hostel life achi h ki gand hi lgi padi h. "+END, "Piggy"});

        ArrayList<Integer>[] hello=encrypt.encrypt(message);
//                Guddu
//                Dukkar
//                Harsh
//                Moti
//                Piggy
//                Shivu

        for (int i = 0; i < hello.length; i++){
            System.out.print(hello[i]);
            System.out.println(hello[i].size());
        }

        Blocker blocker = new Blocker();

        ArrayList<ArrayList<Integer>[]> hey=blocker.generateBlocks(hello,6);

        Polynomial[] polynomials=new Polynomial[hey.size()];

        for (int i = 0; i < hey.size(); i++) {

            polynomials[i]=new PolynomialGenerator().generatePolynomial(hey.get(i));

        }

        Decrypt decrypt = new Decrypt();
        System.out.println(decrypt.decrypt(polynomials,"Guddu"));
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
