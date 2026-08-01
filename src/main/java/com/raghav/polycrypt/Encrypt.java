package com.raghav.polycrypt;

import java.util.ArrayList;

public class Encrypt {
     ArrayList[] encrypt(ArrayList<String[]> messages) {

        ArrayList<Integer> pointsY = new ArrayList<>();
        ArrayList<Integer> pointsX = new ArrayList<>();

         System.out.println(messages.get(0)[0]);
         System.out.println(messages.get(0)[1]);

        messages=padding(messages);//Padded to make rectangle

        for (String[] message : messages) {
            pointsY.addAll(getPointsY(message[0]));
            pointsX.addAll(getPointsX(message[1], message[0].length()));
        }

        return new ArrayList[]{pointsX,pointsY};

    }

    ArrayList<String[]> padding(ArrayList<String[]> messages) {
         int max=-9999;
         for(int i=0;i<messages.size();i++) {
             max=Math.max(max,messages.get(i)[0].length());
         }

         for (int i=0;i<messages.size();i++) {
             String message=messages.get(i)[0];
             if(message.length()<max) {
                 StringBuilder stringBuilder=new StringBuilder(message);
                 int len=max-stringBuilder.length();
                 for(int j=0;j<len;j++) {
                     stringBuilder.append('░');
                 }
                 messages.set(i,new String[]{stringBuilder.toString(),messages.get(i)[1]});
             }

         }
         return messages;
    }

    ArrayList<Integer> getPointsY(String message) {

        ArrayList<Integer> pointsY = new ArrayList<>();
        char[] messageInput = message.toCharArray();

        for (char c : messageInput) {
            pointsY.add((int) c);
        }
        return pointsY;
    }

    ArrayList<Integer> getPointsX(String message, int lengthOfMessage) {
        System.out.println(lengthOfMessage);
        ArrayList<Integer> pointsX = new ArrayList<>();
        char[] messageInput = message.toCharArray();
        int count = 0;

        for (int i = 0; i < lengthOfMessage;) {

            for (int j = 0; j < messageInput.length && i < lengthOfMessage; j++) {
                System.out.println(messageInput[j] + "" + j + "" + count);

                int x = Math.abs(generateHash(messageInput[j] + "" + j + "" + count));

                // Alternate sign
                if ((i & 1) == 1) {
                    x = -x;
                }
                System.out.printf("ENC %-3s -> %d%n",
                        messageInput[j] + "" + j + "" + count,
                        x);
                pointsX.add(x);
                i++;
            }
            count++;
        }

        return pointsX;
    }



    int generateHash(String message) {
        return message.hashCode();
    }

}
