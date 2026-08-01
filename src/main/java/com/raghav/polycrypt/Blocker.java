package com.raghav.polycrypt;

import org.unbescape.json.JsonEscapeType;

import java.util.ArrayList;

public class Blocker {
    static int MAX=test.MAX;
                        //Keys         //Messages
     ArrayList<ArrayList<Integer>[]> generateBlocks(ArrayList<Integer>[] XY,int height) {


         System.out.println(height);

         System.out.println("MAX:"+MAX+""+height);
         int width=MAX/height;
         System.out.println(width);
         int row=XY[1].size()/ height;
         System.out.println("Hello");


        //Y,X of Each Block;
        ArrayList<ArrayList<Integer>[]> blocks=new ArrayList<>();

        for(int j=0;j<XY[1].size();j++) {
            int rowNumber =j/row;
            System.out.println("Hello");
            int index=j-(row*rowNumber);
            System.out.println("WTF");
            int blockNumber= index /width;
            System.out.println("Hello");


            while (blocks.size() <= blockNumber) {

                ArrayList<Integer>[] block = new ArrayList[2];
                block[0] = new ArrayList<>(); // x
                block[1] = new ArrayList<>(); // y

                blocks.add(block);
            }

            blocks.get(blockNumber)[0].add(XY[0].get(j));

            if(XY[1].get(j)==test.START) {
                blocks.get(blockNumber)[1].add(width);
            }
            else
               blocks.get(blockNumber)[1].add(XY[1].get(j));

        }

         System.out.println("BYE bye");
        return blocks;


    }

    //a b c * *        1 2 3
    //d e f g h        4 5 6
    //i j k l *        7 8 9
}
